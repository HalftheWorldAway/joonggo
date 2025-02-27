package com.project.app.inquire.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.app.comment.domain.CommentVO;
import com.project.app.inquire.domain.InquireVO;

@Mapper
public interface InquireDAO {

	// 문의 내역 조회
	List<InquireVO> inquire_list();
	
	// 파일첨부가 없는 경우의 1:1문의
	int add(InquireVO inquirevo);
	
	// 파일첨부가 있는 경우의 1:1문의
	int add_withFile(InquireVO inquirevo);

	// 문의 상세보기
	InquireVO inquire_detail(Map<String, String> paraMap);

	// 답변조회
	List<CommentVO> get_inquire_comment(String pk_inquire_no);

	

}
