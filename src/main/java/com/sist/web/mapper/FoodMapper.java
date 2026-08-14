package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
/*
 * 	스프링에 메모리 할당 요청
 * 	1. @Repository : 데이터베이스 연동 => 구분 (저장소)
 * 	2. @Service : 요청 처리 => BI
 * 	3. @Controller : 화면 변경
 * 		@RestController : JavaScript(Ajax , axios , fetch)	
 * 							결과값 전송
 * 							=> JSON / 일반 문자열
 * 	4. @Component : AOP / Manager => 일반 클래스
 * 	5. @ControllerAdvice
 * 		@RectControllerAdvice ==> 예외처리
 * 	6. @Configuration : 자바 환경 설정
 * 		= 스프링 보안 (JWT)
 * 		= WebSocket 설정
 * 		= QueryDSL 설정
 * 	--------------------------- @Component => 구분을 하고 있다
 * 
 */

import java.util.*;
import com.sist.web.vo.*;

@Mapper
@Repository	// 이게 없으면 메모리할당이 되지 않는다
public interface FoodMapper {
	
	// 여러줄이거나 복잡한 SQL 문장은 xml 파일에서 작성하고, 간단한 한 줄 짜리 SQL 문장은 @Select 등을 이용해 작성
	
	/*
	 * 	<select id="foodListData" resultType="com.sist.web.vo.FoodVO" parameterType="int">
		 	SELECT no,name,poster,address
		 	FROM food
		 	ORDER BY no ASC
		 	OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		 </select>
	 */
	public List<FoodVO> foodListData(int start);
	// selectList
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food")
	public int foodTotalPage();
	
	// 상세보기
	@Select("SELECT * FROM food WHERE no=#{no}")
	public FoodVO foodDetailData(int no);
	// => selectOne
	
	@Update("UPDATE food SET hit=hit+1 WHERE no=#{no}")
	public void foodHitIncrement(int no);
}
