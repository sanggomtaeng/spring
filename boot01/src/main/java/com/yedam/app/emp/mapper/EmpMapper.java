package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.emp.service.EmpVO;

@Mapper
public interface EmpMapper {
	// DAO - Data Accept Object를 위한 Interface
	
	// 전체조회 - SELECT문, 조건X, 결과가 여러건
	public List<EmpVO> selectEmpAll();
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	// 수정
	public int updateEmpInfo(EmpVO empVO);
	// 삭제
	public int deleteEmpInfo(int empId);
}
