package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.ExternalStaff;

@Service
public interface IExternalStaffService {
	public List<ExternalStaff>  getAllExtStaffs() throws Exception;
	public List<ExternalStaff>  getActiveExtStaffs() throws Exception;
	public int getNextExtStaffId() throws Exception;
	public String deleteExtStaff(int extStaffId) throws Exception;
	public String createExtStaff(ExternalStaff externalStaff) throws Exception;
	public String updateExtStaff(ExternalStaff externalStaff) throws Exception;


}
