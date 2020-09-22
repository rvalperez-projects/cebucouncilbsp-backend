package com.cebucouncilbsp.backend.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.cebucouncilbsp.backend.constant.ISComPositionCategoryCode;
import com.cebucouncilbsp.backend.constant.InstitutionCategoryCode;
import com.cebucouncilbsp.backend.entity.ISComDetailsEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.SystemFailureException;
import com.cebucouncilbsp.backend.requestdto.UserSignUpRequestForm;
import com.cebucouncilbsp.backend.utils.DateUtils;
import com.cebucouncilbsp.backend.utils.TextUtils;

/**
 * @author reneir.val.t.perez
 *
 */
@Component
public class EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String cebuCouncilEmailAddress;

	@Value("${email.aurSubmission.subject}")
	private String aurSubmissionSubject;
	@Value("${email.aurReceipt.subject}")
	private String aurReceiptSubject;
	@Value("${email.signup.subject}")
	private String signUpSubject;

	@Value("${email.payment.landbank.accountName}")
	private String landbankAccountName;
	@Value("${email.payment.landbank.accountNumber}")
	private String landbankAccountNumber;

	@Value("${email.payment.remittance.accountName}")
	private String remittanceAccountName;
	@Value("${email.payment.remittance.accountNumber}")
	private String remittanceAccountNumber;

	public void sendRegistrationInfo(UserSignUpRequestForm user) {

		MimeMessage message = emailSender.createMimeMessage();

		Map<String, Object> model = new HashMap<>();
		// Set Login Details
		model.put("username", user.getUsername());
		model.put("password", user.getPassword());

		// Set Personal Information
		model.put("scouterName",
				TextUtils.getFullName(user.getSurname(), user.getGivenName(), user.getMiddleInitial()));
		model.put("mobileNumber", user.getMobileNumber());
		model.put("emailAddress", user.getEmailAddress());

		// Set Institution
		model.put("institution", user.getInstitutionName());
		model.put("address", user.getAddress());
		model.put("category", InstitutionCategoryCode.get(user.getCategoryCode()).name());
		model.put("district", user.getDistrict());
		model.put("area", user.getArea());
		model.put("contactNumber", user.getContactNumber());

		Context context = new Context();
		context.setVariable("bannerImage", "bannerImage");
		context.setVariables(model);

		String html = templateEngine.process("email-signup-receipt-template", context);

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setSubject(signUpSubject);
			helper.setFrom(cebuCouncilEmailAddress);
			helper.setTo(user.getEmailAddress());
			helper.setText(html, true);

			// Add Attachment
			helper.addInline("bannerImage", new ClassPathResource("/email/img/banner-bsp-webpage.png"), "image/png");

		} catch (MessagingException e) {
			LOGGER.error("Sign Up Email Sending Failed");
			throw new SystemFailureException("Sign Up Email Sending Failed");
		}

		try {
			emailSender.send(message);
		} catch (MailSendException mailSendException) {
			LOGGER.error("Email Sending Failed");
			throw new SystemFailureException("Email Sending Failed");
		}
	}

	public void sendAURSubmissionEmail(UserEntity user, InstitutionEntity institution,
			UnitRegistrationEntity unitRegistration) {

		MimeMessage message = emailSender.createMimeMessage();

		Map<String, Object> model = new HashMap<>();
		this.setAURSubmissionValuesToHtml(model, user, institution, unitRegistration);

		Context context = new Context();
		context.setVariables(model);
		context.setVariable("bannerImage", "bannerImage");
		context.setVariable("logoLandbank", "logoLandbank");
		context.setVariable("logoGcash", "logoGcash");
		context.setVariable("logoPalawan", "logoPalawan");
		context.setVariable("logoRd", "logoRd");
		context.setVariable("logoML", "logoML");
		context.setVariable("uploadPayment", "uploadPayment");

		String html = templateEngine.process("email-aur-submission-template", context);

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setSubject(aurSubmissionSubject);
			helper.setFrom(cebuCouncilEmailAddress);
			helper.setTo(user.getEmailAddress());
			helper.setText(html, true);

			// Add Attachment
			helper.addInline("bannerImage", new ClassPathResource("/email/img/banner-bsp-webpage.png"), "image/png");
			helper.addInline("logoLandbank", new ClassPathResource("/email/img/logo-landbank.png"), "image/png");
			helper.addInline("logoGcash", new ClassPathResource("/email/img/logo-gcash.png"), "image/png");
			helper.addInline("logoPalawan", new ClassPathResource("/email/img/logo-palawan.jpg"), "image/jpg");
			helper.addInline("logoRd", new ClassPathResource("/email/img/logo-rd-padala.png"), "image/png");
			helper.addInline("logoML", new ClassPathResource("/email/img/logo-m-lhuillier.png"), "image/png");
			helper.addInline("uploadPayment", new ClassPathResource("/email/img/img-upload-payment.png"), "image/png");

		} catch (MessagingException e) {
			LOGGER.error("Email Creation Failed");
			throw new SystemFailureException("Email Creation Failed");
		}

		try {
			emailSender.send(message);
		} catch (MailSendException mailSendException) {
			LOGGER.error("Email Sending Failed");
		}
	}

	public void sendAURReceiptEmail(MultipartFile receipt, UserEntity user, InstitutionEntity institution,
			UnitRegistrationEntity unitRegistration) {

		MimeMessage message = emailSender.createMimeMessage();

		Map<String, Object> model = new HashMap<>();
		// Set Variables
		model.put("scouterName",
				String.format("%s %s. %s", user.getGivenName(), user.getMiddleInitial(), user.getSurname()));
		model.put("scouterInstitution", institution.getInstitutionName());
		model.put("district", institution.getDistrict());
		model.put("area", institution.getArea());
		model.put("applicationDate", DateUtils.getFormattedDate(unitRegistration.getDateApplied()));
		model.put("unitNumber", unitRegistration.getUnitNumber());

		// Get Total Amount
		RegistrationFees.calculateFees(unitRegistration);
		model.put("totalFeesAmount", RegistrationFees.totalFeesAmount);

		Context context = new Context();
		context.setVariables(model);
		context.setVariable("receipt", receipt.getName());

		String html = templateEngine.process("email-aur-receipt-template", context);

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setFrom(cebuCouncilEmailAddress);
			helper.setSubject(aurReceiptSubject.replace("<unitNumber>", unitRegistration.getUnitNumber())
					.replace("<institution>", institution.getInstitutionName()));
			helper.setTo(cebuCouncilEmailAddress);
			helper.setText(html, true);

			// Add Attachment
			final InputStreamSource imageSource = new ByteArrayResource(receipt.getBytes());
			helper.addInline(receipt.getName(), imageSource, receipt.getContentType());

		} catch (MessagingException | IOException e) {
			LOGGER.error("Email Creation Failed");
			throw new SystemFailureException("Email Creation Failed");
		}

		try {
			emailSender.send(message);
		} catch (MailSendException mailSendException) {
			LOGGER.error("Email Sending Failed");
			throw new SystemFailureException("Email Sending Failed");
		}
	}

	private void setAURSubmissionValuesToHtml(Map<String, Object> model, UserEntity user, InstitutionEntity institution,
			UnitRegistrationEntity unitRegistration) {
		RegistrationFees.calculateFees(unitRegistration);

		model.put("scouterName",
				TextUtils.getFullName(user.getSurname(), user.getGivenName(), user.getMiddleInitial()));
		model.put("scouterInstitution", institution.getInstitutionName());
		model.put("aurFormSubmissionDate", DateUtils.getFormattedDate(unitRegistration.getDateApplied()));
		model.put("iscomRepCount", RegistrationFees.iSComRepCount);
		model.put("iscomRepAmount", RegistrationFees.iSComRepAmount);
		model.put("iscomCoordCount", RegistrationFees.iSComCoordCount);
		model.put("iscomCoordAmount", RegistrationFees.iSComCoordAmount);
		model.put("iscomUnitLeadersCount", RegistrationFees.iSComUnitLeadersCount);
		model.put("iscomUnitLeadersAmount", RegistrationFees.iSComUnitLeadersAmount);
		model.put("iscomCharterCount", RegistrationFees.iSComCharterCount);
		model.put("iscomCharterAmount", RegistrationFees.iSComCharterAmount);
		model.put("unitScoutsCount", RegistrationFees.unitScoutsCount);
		model.put("unitScoutsAmount", RegistrationFees.unitScoutsAmount);
		model.put("totalFeesAmount", RegistrationFees.totalFeesAmount);
		model.put("landbankAccountName", landbankAccountName);
		model.put("landbankAccountNumber", landbankAccountNumber);
		model.put("remittanceAccountName", remittanceAccountName);
		model.put("remittanceAccountNumber", remittanceAccountNumber);
	}

	static class RegistrationFees {
		static int iSComRepCount;
		static float iSComRepAmount;
		static int iSComCoordCount;
		static float iSComCoordAmount;
		static int iSComUnitLeadersCount;
		static float iSComUnitLeadersAmount;
		static int iSComCharterCount;
		static float iSComCharterAmount;
		static int unitScoutsCount;
		static float unitScoutsAmount;
		static float totalFeesAmount;

		// private constructor
		private RegistrationFees() {
		}

		static void initializeFees() {
			iSComRepCount = 0;
			iSComRepAmount = 0.00f;
			iSComCoordCount = 0;
			iSComCoordAmount = 0.00f;
			iSComUnitLeadersCount = 0;
			iSComUnitLeadersAmount = 0.00f;
			iSComCharterCount = 0;
			iSComCharterAmount = 0.00f;
			unitScoutsCount = 0;
			unitScoutsAmount = 0.00f;
			totalFeesAmount = 0.00f;
		}

		/**
		 * Calculates Registration Fees.
		 *
		 * @param unitRegistration
		 */
		static void calculateFees(UnitRegistrationEntity unitRegistration) {
			initializeFees();

			// Count ISCOM Members
			for (ISComDetailsEntity iscom : unitRegistration.getIscomMembersList()) {
				switch (ISComPositionCategoryCode.get(iscom.getPositionCode())) {
				case ISCOM_REP:
				case PARENT_REP:
					iSComRepCount++;
					break;
				case ISC_CHAIR:
				case ISC_COORDINATOR:
					iSComCoordCount++;
					break;
				case UNIT_LEADER:
				case ASST_UNIT_LEADER:
					iSComUnitLeadersCount++;
					break;
				}
			}

			// Get Charter Flag and Unit Scouts Count
			iSComCharterCount = unitRegistration.getCharterFlag() ? 1 : 0;
			if (!CollectionUtils.isEmpty(unitRegistration.getUnitMembersList())) {
				unitScoutsCount = unitRegistration.getUnitMembersList().size();
			}

			// Set Sub-Total Amount
			iSComRepAmount = iSComRepCount * 100.00f;
			iSComCoordAmount = iSComCoordCount * 100.00f;
			iSComUnitLeadersAmount = iSComUnitLeadersCount * 60.00f;
			iSComCharterAmount = iSComCharterCount * 10.00f;
			unitScoutsAmount = unitScoutsCount * 50.00f;

			// Set Total Amount
			totalFeesAmount = iSComRepAmount + iSComCoordAmount + iSComUnitLeadersAmount + iSComCharterAmount
					+ unitScoutsAmount;
		}
	}

}
