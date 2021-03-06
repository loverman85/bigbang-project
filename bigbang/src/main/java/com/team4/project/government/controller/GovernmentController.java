package com.team4.project.government.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.team4.project.HomeController;
import com.team4.project.government.dto.GoCitizen;
import com.team4.project.government.dto.GoHospital;
import com.team4.project.government.dto.GoMedicine;

import com.team4.project.util.HttpUrlCon;

@RestController
public class GovernmentController {
	private static final Logger logger = LoggerFactory.getLogger(GovernmentController.class);
	@Autowired
	private GovernmentService goService;
	private Gson gson = new Gson();

	// 주민번호 있는지 확인후 true or false리턴(test)
	@RequestMapping(value = "/government/checkCitizenId", method = RequestMethod.GET, 
					produces = "text/json; charset=UTF-8")
	public String checkCitizenId(String citizenId, String citizenName, String test) {
		GoCitizen gc = goService.checkCitizenId(citizenId);
		// gc가 널이아니면(조회결과가 있으면 = 주민번호가 맞으면)
		if (gc != null) {
			logger.debug("주민번호는 맞음");
			// 주민번호는 맞으나 이름이 틀리면
			if (!gc.getGoCitizenName().equals(citizenName)) {
				logger.debug("주민번호는 맞으나 이름이 틀렸습니다.");
				return "nameIncorrect";
				// 주민번호도 맞고 이름도 맞으면
			} else {
				logger.debug("주민번호, 이름 모두일치");
				return "allCorrect";
			}
			// gc가 널이면(주민번호가 없으면 = 조회결과가 없으면)
		} else {
			logger.debug("없는 주민번호 입니다.");
			return "idIncorrect";
		}
	}

	// 주민번호 있는지 확인후 true or false리턴(test)
	@RequestMapping(value = "/government/checkCitizenId", method = RequestMethod.POST,
					produces = "text/json; charset=UTF-8")
	public String checkCitizenId(String citizenId, String citizenName) {
		GoCitizen gc = goService.checkCitizenId(citizenId);
		// gc가 널이아니면(조회결과가 있으면 = 주민번호가 맞으면)
		if (gc != null) {
			logger.debug("주민번호는 맞음");
			// 주민번호는 맞으나 이름이 틀리면
			if (!gc.getGoCitizenName().equals(citizenName)) {
				logger.debug("주민번호는 맞으나 이름이 틀렸습니다.");
				return "nameIncorrect";
				// 주민번호도 맞고 이름도 맞으면
			} else {
				logger.debug("주민번호, 이름 모두일치");
				return "allCorrect";
			}
			// gc가 널이면(주민번호가 없으면 = 조회결과가 없으면)
		} else {
			logger.debug("없는 주민번호 입니다.");
			return "idIncorrect";
		}
	}

	// 파일업로드 테스트
	@RequestMapping(value = "/getFile", method = RequestMethod.POST)
	public String getURL(String id, String name, MultipartFile file, MultipartFile file2) {
		logger.debug("/getFile 들어옴!");
		logger.debug("id:" + id);
		logger.debug("name:" + name);
		logger.debug("file:" + file);
		logger.debug("file2:" + file2);
		return "";
	}

	// 1약코드 가져오기 POST
	@RequestMapping(value = "/government/getMedicineCode", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public String getMedicineCode() {
		logger.debug("getMedicineCode POST 진입");
		List<Map> list = goService.getMedicineList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);

		// logger.debug("List<GoMedicine>:"+list);
		return jsonStr;
	}

	// 1약코드 가져오기 GET
	@RequestMapping(value = "/government/getMedicineCode", method = RequestMethod.GET, produces = "text/json; charset=UTF-8")
	public String getMedicineCode(String test) {
		logger.debug("getMedicineCode GET 진입");
		List<Map> list = goService.getMedicineList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 2질병코드 가져오기 POST
	@RequestMapping(value = "/government/getDiseaseCode", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public String getDiseaseCode() {
		logger.debug("getDiseaseCode GET 진입");
		List<Map> list = goService.getDiseaseList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 2질병코드 가져오기 GET
	@RequestMapping(value = "/government/getDiseaseCode", method = RequestMethod.GET, produces = "text/json; charset=UTF-8")
	public String getDiseaseCode(String test) {
		logger.debug("getDiseaseCode GET 진입");
		List<Map> list = goService.getDiseaseList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 3진료과목코드 가져오기 POST
	@RequestMapping(value = "/government/getTreatSubjectCode", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public String getTreatSubjectCode() {
		logger.debug("getTreatSubjectCode POST 진입");
		List<Map> list = goService.getTreatSubjectList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 3진료과목코드 가져오기 GET
	@RequestMapping(value = "/government/getTreatSubjectCode", method = RequestMethod.GET, produces = "text/json; charset=UTF-8")
	public String getTreatSubjectCode(String test) {
		logger.debug("getTreatSubjectCode GET 진입");
		List<Map> list = goService.getTreatSubjectList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 4수술코드 가져오기 POST
	@RequestMapping(value = "/government/getSurgeryCode", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public String getSurgeryCode() {
		logger.debug("getTreatSubjectCode POST 진입");
		List<Map> list = goService.getSurgeryList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 4수술코드 가져오기 GET
	@RequestMapping(value = "/government/getSurgeryCode", method = RequestMethod.GET, produces = "text/json; charset=UTF-8")
	public String getSurgeryCode(String test) {
		logger.debug("getTreatSubjectCode GET 진입");
		List<Map> list = goService.getSurgeryList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 5예방접종코드 가져오기 POST
	@RequestMapping(value = "/government/getVaccinationCode", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public String getVaccinationCode() {
		logger.debug("getTreatSubjectCode POST 진입");
		List<Map> list = goService.getVaccinationList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 5예방접종코드 가져오기 GET
	@RequestMapping(value = "/government/getVaccinationCode", method = RequestMethod.GET, produces = "text/json; charset=UTF-8")
	public String getVaccinationCode(String test) {
		logger.debug("getTreatSubjectCode GET 진입");
		List<Map> list = goService.getVaccinationList();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	// 약코드가져오는 controller 호출하기
	@RequestMapping(value = "/government/getMedicine", method = RequestMethod.GET)
	public String getMedicineHochul() {
		logger.debug("getMedicine 진입");

		HttpUrlCon huc = new HttpUrlCon("http://localhost/project/government/getMedicineCode?id=dd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "11");
		map.put("name", "똥꾸");
		try {
			String result = huc.HttpUrlGET();
			logger.debug("result:" + result);
			Gson gson = new Gson();

			// json to list 방법1
			GoMedicine[] array = gson.fromJson(result, GoMedicine[].class);
			List<GoMedicine> list = Arrays.asList(array);
			for (int i = 0; i < list.size(); i++) {
				// GoMedicine goMedicine = gson.fromJson(list.get(i),
				// GoMedicine.class);
				logger.debug("코드:" + list.get(i).getGoMedicineCode() + " 이름:" + list.get(i).getGoMedicineName());
			}

			/*
			 * // json to list 방법2 List<GoMedicine> list2 =
			 * gson.fromJson(result, new
			 * TypeToken<List<GoMedicine>>(){}.getType());
			 * logger.debug("=======list 돌리기======"); for(int i=0;
			 * i<list2.size();i++){ //GoMedicine goMedicine =
			 * gson.fromJson(list.get(i), GoMedicine.class);
			 * logger.debug("코드:"+list2.get(i).getGoMedicineCode()+
			 * " 이름:"+list2.get(i).getGoMedicineName()); }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * HttpURLConnection conn = null; try { URL url = new
		 * URL("http://localhost/project/government/getMedicineCode"); conn =
		 * (HttpURLConnection) url.openConnection();
		 * conn.setRequestMethod("GET"); // 요청 방식을 설정 (default : GET)
		 * 
		 * conn.setDoInput(true); // input을 사용하도록 설정 (default : true)
		 * conn.setDoOutput(true); // output을 사용하도록 설정 (default : false)
		 * 
		 * conn.setConnectTimeout(60); // 타임아웃 시간 설정 (default : 무한대기)
		 * conn.connect(); InputStream in = conn.getInputStream();
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(conn.getInputStream(), "UTF-8")); // 캐릭터셋 설정
		 * StringBuilder sb = new StringBuilder(); String line = null; while
		 * ((line = br.readLine()) != null) { if (sb.length() > 0) {
		 * sb.append("\n"); } sb.append(line); } logger.debug("response:"
		 * + sb.toString());
		 * 
		 * 
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		return "";
	}

	// 유민이꺼 혈액검사 검색조건 보내주기 테스트
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		/*
		 * Gson gson = new Gson(); String jsonStr = gson.toJson(); Map<String,
		 * String> map = new HashMap<String, String>();
		 * map.put("imageTestSearch", jsonStr);
		 */
		HttpUrlCon huc = new HttpUrlCon("http://192.168.123.147/project/government/goImageTest");
		try {
			// String result = huc.HttpUrlPOST(map);
			// logger.debug("result:"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
