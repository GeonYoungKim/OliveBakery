package kr.ac.controller;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.ac.controller.board.dto.BoardMapDTO;
import kr.ac.controller.board.dto.ChatVO;
import kr.ac.controller.board.dto.CheckRoomVO;
import kr.ac.controller.board.dto.InChargePatientVO;
import kr.ac.controller.board.dto.JsonResult;
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
import net.sf.json.JSONObject;

@ContextConfiguration(locations= {		
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})

@Controller
public class MainController {	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired private kr.ac.controller.board.dao.UserDAO UserDaoImpl=null;	
	
	
	
	
	@RequestMapping("/Admin")
	public void userList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter( "a" );
		
		if("list".equals(actionName)){
			System.out.println("list");
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(UserDaoImpl.getUserList()));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString()); 
		}else if("delete".equals(actionName)) {
			System.out.println("delete");
			String id=request.getParameter("id");
			UserDaoImpl.userDelete(id);	
		}else if("breadlist".equals(actionName)) {
			TimeVO timeVO=new TimeVO();
			System.out.println("breadlist");
			String startdate = request.getParameter( "startdate" );
			long sd=Long.parseLong(startdate);
			sd=sd*10000;
			String enddate = request.getParameter( "enddate" );
			long ed=Long.parseLong(enddate);
			ed=ed*10000;
			ed=ed+2359;			
			timeVO.setTime1(sd);
			timeVO.setTime2(ed);
			System.out.println(sd);
			System.out.println(ed);
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(UserDaoImpl.AndroidList(timeVO)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString()); 
		}else if("reservationlist".equals(actionName)) {
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(UserDaoImpl.AndroidReservationList()));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString()); 
		}else if("lookup".equals(actionName)) {
			String id=request.getParameter("id");
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(UserDaoImpl.AndroidLookupList(id)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString()); 
		}else if("reservationdelete".equals(actionName)) {
			String id=request.getParameter("id");
			UserDaoImpl.reservationDelete(id);
		}
	}
	
	
	@RequestMapping("/OrderHistory")
	public String OrderHistory(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		ReservationVO vo=new ReservationVO();
		vo.setId((String)session.getAttribute("id"));
		request.setAttribute("reservationlist", UserDaoImpl.getReservationList(vo));
		return "OrderHistory";
	}
	@RequestMapping("/AddressBookForm")
	public String AddressBookForm(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		UserVO uservo=UserDaoImpl.getUser((String)session.getAttribute("id"));
		request.setAttribute("uservo", uservo);
		return "AddressBookForm";
	}
	
	@RequestMapping("/AddressBook")
	public String AddressBook(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		String id=(String)session.getAttribute("id");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String address3=request.getParameter("address3");
		UserVO vo=new UserVO();
		vo.setAddress1(address1);
		vo.setAddress2(address2);
		vo.setAddress3(address3);
		vo.setId(id);
		UserDaoImpl.updateUserAddress(vo);
		return "redirect:/AddressBookForm";
	}
	@RequestMapping("/EditUser")
	public String EditUser(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String phone=request.getParameter("phone");
		String id=(String)session.getAttribute("id");
		UserVO vo=new UserVO();
		vo.setBirth(birth);
		vo.setId(id);
		vo.setName(name);
		vo.setPhone(phone);
		UserDaoImpl.updateUser(vo);
		return "redirect:/EditUserForm";
	}
	@RequestMapping("/EditUserForm")
	public String EditUserForm(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		UserVO uservo=UserDaoImpl.getUser((String)session.getAttribute("id"));
		request.setAttribute("uservo", uservo);
		return "EditUserForm";
	}
	@RequestMapping("/EditPassword")
	public String EditPassword(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		String password=request.getParameter("password");
		String newpassword=request.getParameter("newpassword");
		String newpassword2=request.getParameter("newpassword2");
		String passworderror=null;
		String newpassworderror=null;
		String changepassword=null;
		
		UserVO vo=UserDaoImpl.getUser((String)session.getAttribute("id"));
		if(password.equals(vo.getPassword())) {
			if(newpassword.equals(newpassword2)) {
				vo.setId((String)session.getAttribute("id"));
				vo.setPassword(newpassword);
				UserDaoImpl.updatePassword(vo);
				changepassword="비밀번호가 변경되었습니다.";
				request.setAttribute("changepassword",changepassword);
				return "EditPasswordForm";
			}else {
				//새비밀번호 확인 다름
				newpassworderror="새비밀번호 확인 틀림";
				request.setAttribute("newpassworderror",newpassworderror);
				return "EditPasswordForm";
			}
		}else {
			//기존비밀번호 틀림
			passworderror="기존비밀번호 틀림";
			request.setAttribute("passworderror",passworderror);
			return "EditPasswordForm";
		}
	}
	@RequestMapping("/EditPasswordForm")
	public String EditPasswordForm(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		return "EditPasswordForm";
	}
	@RequestMapping("/Logout")
	public String Logout(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		session.removeAttribute("id");		
		return "Main";
	}
	@RequestMapping("/Login")
	public String Login(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException  {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		UserVO vo=UserDaoImpl.getUser(id);
		
		if(vo==null) {
			return "LoginForm";
		}else {
			session.setAttribute("id", id);
			request.setAttribute("id", id);
			return "Main";
		}
	}
	@RequestMapping("/MemberShip")
	public String MemberShip(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		UserVO vo=new UserVO();
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String phone=request.getParameter("phone");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String address3=request.getParameter("address3");
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setBirth(birth);
		vo.setPhone(phone);
		vo.setAddress1(address1);
		vo.setAddress2(address2);;
		vo.setAddress3(address3);
		UserDaoImpl.insertUser(vo);
		System.out.println(name);
		return "Main";
	}	
	@RequestMapping("/Main")
	public String Main() {
		System.out.println("Main");
		return "Main";
	}
	@RequestMapping("/Menu")
	public String Menu() {
		System.out.println("Menu");
		return "Menu";
	}
	@RequestMapping("/Notice")
	public String Notice() {
		System.out.println("Notice");
		return "Notice";
	}
	
	@RequestMapping("/Reservation")
	public String Reservation(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		System.out.println("Reservation");
		
		String id=(String)session.getAttribute("id");
		if(id==null) {
			return "LoginForm";
		}else {
			UserVO uservo=UserDaoImpl.getUser(id);
			request.setAttribute("uservo",uservo);			
			return "Reservation";
		}
	}
	@RequestMapping("/MemberShipForm")
	public String MemberShipForm() {
		System.out.println("MemberShipForm");
		return "MemberShipForm";
	}
	@RequestMapping("/LoginForm")
	public String LoginForm() {
		System.out.println("LoginForm");
		return "LoginForm";
	}
	
	@RequestMapping("/CompleteReservation")
	public String CompleteReservation(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException, SQLException {
		request.setCharacterEncoding("utf-8");
		
		String[] breadnames= {"q","w","e","r"};
		ReservationVO vo=new ReservationVO();
		System.out.println("CompleteReservation");
		UpdateBread updateBread=new UpdateBread();
		String address=(String)request.getParameter("address");
		String requirement=(String)request.getParameter("requirement");
		String nowTime = getCurrentTime("YYYY:MM:dd:HH:mm");
		String nowTime2 = nowTime.replaceAll(":", "");
		System.out.println(nowTime2);
		long time=Long.parseLong(nowTime2.trim());
		
		System.out.println(time);
		for(int i=0;i<breadnames.length;i++) {
			if("".equals(request.getParameter(i+""))){
				continue;
			}
			System.out.println(request.getParameter(i+""));
			vo.setBreadname(breadnames[i]);
			vo.setBreadquantity(Integer.parseInt(request.getParameter(i+"")));
			vo.setAddress(address);
			vo.setRequirement(requirement);
			vo.setId((String)session.getAttribute("id"));
			vo.setTime(time);
			UserDaoImpl.insertReservation(vo);
			updateBread.setBreadname(breadnames[i]);
			updateBread.setBreadquantity(Integer.parseInt(request.getParameter(i+"")));
			UserDaoImpl.updateBread(updateBread);
		}	
		vo.setId((String)session.getAttribute("id"));
		
		request.setAttribute("address", address);
		request.setAttribute("requirement", requirement);
		request.setAttribute("reservationlist", UserDaoImpl.getReservationList(vo));
		request.setAttribute("time", time);
		return "CompleteReservation";
		
	}	
	public static String getCurrentTime(String timeFormat){
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }
}
