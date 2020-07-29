/**
 *
 */
package com.cebucouncilbsp.backend.entity;

import java.time.LocalDateTime;

/**
 * @author reneir.val.t.perez
 *
 */
public class BaseEntity {

	private String createdBy;
	private LocalDateTime createdDateTime;
	private String udpatedBy;
	private LocalDateTime updatedDateTime;

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getUdpatedBy() {
		return udpatedBy;
	}
	public void setUdpatedBy(String udpatedBy) {
		this.udpatedBy = udpatedBy;
	}
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
}
