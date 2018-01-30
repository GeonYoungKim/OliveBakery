package kr.ac.controller.board.dao;

import java.util.List;

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

public interface UserDAO {
	public void insertUser(kr.ac.controller.board.dto.UserVO userVO);
	public List<UserVO> getUserList();
	public UserVO getUser(String id);
	public void updatePassword(UserVO uservo);
	public void updateUser(kr.ac.controller.board.dto.UserVO userVO);
	public void updateUserAddress(kr.ac.controller.board.dto.UserVO userVO);
	public void insertReservation(ReservationVO ReservationVO);
	public void getCountList(String id);
	public List<ReservationVO> getReservationList(ReservationVO reservationVO);
	public void updateBread(UpdateBread updateBread);
	public void userDelete(String id);
	public List<BreadVO> getBreadList();
	public List<AndroidReservationVO> AndroidList(TimeVO timeVO);
	public List<String> AndroidReservationList();
	public List<ReservationVO> AndroidLookupList(String id);
	public void reservationDelete(String id);
	
	
}
