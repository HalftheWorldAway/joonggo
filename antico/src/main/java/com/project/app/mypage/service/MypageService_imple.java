package com.project.app.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.mypage.model.MypageDAO;

@Service
public class MypageService_imple implements MypageService {

    @Autowired
    private MypageDAO mypagedao;
    
	// 탈퇴 테이블 탈퇴신청시 insert
	@Override
	public int delete_submit(Map<String, String> paraMap) {
		int n = mypagedao.delete_submit(paraMap);
		return n;
	}

	// 포인트 충전
	@Override
	public int pointcharge(Map<String, String> paraMap) {
		int n = mypagedao.pointcharge(paraMap); // 충전테이블 insert
		int m = 0;
		if(n == 1) {
			m = mypagedao.pointupdate(paraMap); // 회원의 포인트 update
		}
		int x = 0;
		if(m == 1) {
			x = mypagedao.point_history(paraMap); // 포인트가 충전되면 포인트내역 테이블에 insert
		}
		return n*m*x;
	}

	// 회원의 총 충전금액을 알아오기 위한 용도 (등급때매)
	@Override
	public int point_sum(String pk_member_no, String charge_price) {
		int n = mypagedao.point_sum(pk_member_no);
		int x = n/1000;
		if(x <= 1700) { // 회원의 총 충전금액이 170이하까지만 업데이트
			int m = mypagedao.score_update(pk_member_no, String.valueOf(Integer.parseInt(charge_price)/1000));
		}
		
		
		return n;
	}

	// 마이페이지 내상품 조회하기
	@Override
	public List<Map<String, String>> myproduct(String pk_member_no) {
		List<Map<String, String>> myproduct_list = mypagedao.myproduct(pk_member_no);
		return myproduct_list;
	}
	
	// 판매자 정보 불러오기
	@Override
	public Map<String, String> sellerList(String n) {
		Map<String, String> seller_list = mypagedao.sellerList(n);
		return seller_list;
	}

	@Override
	public void role_update(String role, String pk_member_no) {
		mypagedao.role_update(role, pk_member_no);
	}
	
}
