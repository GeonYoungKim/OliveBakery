package kr.ac.controller.board.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ac.controller.board.dao.UserDAO;
import kr.ac.controller.board.dto.AndroidReservationVO;
import kr.ac.controller.board.dto.BreadVO;
import kr.ac.controller.board.dto.ChatVO;
import kr.ac.controller.board.dto.CheckRoomVO;
import kr.ac.controller.board.dto.InChargePatientVO;
import kr.ac.controller.board.dto.LongTermScheduleVO;
import kr.ac.controller.board.dto.NurseRoomVO;
import kr.ac.controller.board.dto.NurseVO;
import kr.ac.controller.board.dto.PatientVO;
import kr.ac.controller.board.dto.ReservationVO;
import kr.ac.controller.board.dto.RoomVO;
import kr.ac.controller.board.dto.SwapRoom;
import kr.ac.controller.board.dto.TimeVO;
import kr.ac.controller.board.dto.UpdateBread;
import kr.ac.controller.board.dto.UpdateNurseRoomFlagVO;
import kr.ac.controller.board.dto.UserVO;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDAO {
	public void insertUser(UserVO userVO) {
		getSqlSession().insert("User.insertUser", userVO);
	}
	public List<UserVO> getUserList() {
		return getSqlSession().selectList("User.getUserList");
	}
	public UserVO getUser(String id) {
		return getSqlSession().selectOne("User.getUser",id);
	}
	public void updatePassword(UserVO uservo) {
		getSqlSession().update("User.updatePassword",uservo);
	}
	public void updateUser(kr.ac.controller.board.dto.UserVO userVO) {
		getSqlSession().update("User.updateUser",userVO);
	}
	public void updateUserAddress(kr.ac.controller.board.dto.UserVO userVO) {
		getSqlSession().update("User.updateUserAddress",userVO);
	}
	public void insertReservation(ReservationVO ReservationVO) {
		getSqlSession().insert("Reservation.insertReservation", ReservationVO);
	}
	public void getCountList(String id) {
		getSqlSession().selectOne("Reservation.getCountList",id);
	}
	public List<ReservationVO> getReservationList(ReservationVO reservationVO) {
		System.out.println("goo");
		return getSqlSession().selectList("Reservation.ReservationList",reservationVO);
	}
	public void updateBread(UpdateBread updateBread) {
		getSqlSession().update("Bread.updateBread",updateBread);
	}
	public void userDelete(String id) {
		getSqlSession().delete("User.userDelete", id);
	}
	public List<BreadVO> getBreadList() {
		return getSqlSession().selectList("Bread.getBreadList");
	}
	public List<AndroidReservationVO> AndroidList(TimeVO timeVO){
		return getSqlSession().selectList("Reservation.AndroidList",timeVO);
	}
	public List<String> AndroidReservationList(){
		return getSqlSession().selectList("Reservation.AndroidReservationList");
	}
	public List<ReservationVO> AndroidLookupList(String id){
		return getSqlSession().selectList("Reservation.AndroidLookupList",id);
	}
	public void reservationDelete(String id) {
		getSqlSession().delete("Reservation.reservationDelete", id);
	}
	
	
	
}

