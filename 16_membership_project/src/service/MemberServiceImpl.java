package service;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import dto.Member;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 왜 Service, Dao 인터페이스를 만들어서 구현했을까?
 * - 인터페이스를 상속 받아 구현하면
 *   모든 자식 클래스가 똑같은 기능을 가지게되어
 *   비슷하게 생김!
 *
 *  -> 언제든지 서로 다른 자식 클래스로 대체 가능!!
 *    ==> 유지보수성 증가
 */

// MemberService를 상속 받아 구현
public class MemberServiceImpl implements MemberService{

    // dao 객체 부모 참조 변수 선언
    private MemberDAO dao = null;
    private String[] gradeArr = {"일반", "골드", "다이아"};


    // 기본 생성자
    // - MemberServiceImpl 객체 생성 시
    //   MemberDAOImpl 객체도 생성
    public MemberServiceImpl() 
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        dao = new MemberDAOImpl();
    }

    

    
    
    
    //********************************
    // 추가, 수정 삭제 기능이 수행되면
    // 무조건 dao.saveFile() 수행!
    //********************************

    // 회원 추가
    @Override
    public boolean addMember(String name, String phone) throws IOException {
    
    	// Member 객체를 만듦
    	Member member = new Member(name,phone,0,0);
    	
    	
    	// 위에서 설정한 이름이 이미 MemberList에 존재한다면 추가X 
    	for(Member mb : dao.getMemberList()) {
    		if(member.getPhone().equals(mb.getPhone())) return false;
    	}
    	// dao패키지의 addMember 메서드를 이용해서 회원 추가
    	dao.addMember(member);
        return true;
    }


    
    
    
    // DAO에서 조회한 memberList를 그대로 반환
    @Override
    public List<Member> getMemberList() {
    	
    	// dao에서 반환 받은 memberList를 그대로 view로 리턴
        return dao.getMemberList();
    }


    
    
    
    // 이름 검색
    @Override
    public List<Member> selectName(String searchName) {
    		
    		List<Member> list = new ArrayList<Member>();
    	
        for(Member mb : dao.getMemberList()) {
        	if(searchName.equals(mb.getName())) {
        		list.add(mb);
        	}
        }
        
        return list;
    }


    
    
    
    // 금액 누적
    @Override
    public String updateAmount(Member target, int acc) throws IOException {

    	int beforeAmount = target.getAmount(); // 이전 금액
    	int afterAmount = beforeAmount+acc;    // 바뀐 금액
    	
    	// 객체에 금액 누적
    	target.setAmount(afterAmount);
    	
    	String str = "";
    	if(afterAmount>1000000 && beforeAmount<1000000) {
    		str = "* 다이아 * 등급으로 변경 되셨습니다";
    	} 
    	else if(afterAmount>100000 && beforeAmount<100000) {
    		str = "* 골드 * 등급으로 변경 되셨습니다";
    	}
        return String.format("%d -> %d\n"
        		+ "%s",beforeAmount,afterAmount,str);
    }


    
    
    
    //회원 정보(전화번호) 수정
    @Override
    public String updateMember(Member target, String phone) throws IOException {
    		
    	String beforePhone = target.getPhone();
    	String afterPhone = phone;
    	String name = target.getName();
    	
    	target.setPhone(afterPhone);
    	
        return String.format("%s님의 전화번호가 변경 되었습니다\n"
        		+ "%s -> %s",name,beforePhone,afterPhone);
    }

    
    
    

    // 회원 탈퇴
    @Override
    public String deleteMember(Member target) throws IOException {
    	
			dao.getMemberList().remove(target);
      return String.format("%s 회원이 탈퇴 처리 되었습니다",target.getName());
    }
    
    




















    
    
}