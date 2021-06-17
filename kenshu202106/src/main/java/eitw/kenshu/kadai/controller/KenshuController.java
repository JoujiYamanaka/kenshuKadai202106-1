package eitw.kenshu.kadai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("eitw.kenshu.kadai.controller.KenshuController")
public class KenshuController {
	/**
	 * 初期表示処理
	 */
	@RequestMapping(value = "/kenshu/index", method = RequestMethod.GET)
	public ModelAndView password(ModelAndView mav, @ModelAttribute KenshuForm kenshuForm) {
		
		Double numericValue = null;
		
		mav.addObject("numericValue", numericValue);
		mav.setViewName("/index");
		return mav;
	}
	
	/**
	 * 数式計算処理
	 */
	@RequestMapping(value = "/kenshu/submit", method = RequestMethod.POST)
	public ModelAndView submit(ModelAndView mav, @ModelAttribute KenshuForm kenshuForm) {
		
		Double numericValue = parseAndCalc(kenshuForm.getFormula());
		
		mav.addObject("numericValue", numericValue);
		mav.setViewName("/index");
		return mav;
	}
	
	/**
	 * 文字列の数式から計算結果を返す
	 * @param formula
	 * @return double
	 */
	private static double parseAndCalc(String formula) {
		//使用されている演算子とインデックスを求める
        char[] opes = { '+', '-', '*', '/' };
        char ope = ' ';
        int opeIdx = 0;
        for (char o : opes) {
            opeIdx = formula.indexOf(o);
            if (opeIdx != -1) {
                ope = formula.charAt(opeIdx);
                break;
            }
        }
 
        // 演算子の前後の数をdouble型に変換
        double x = Double.parseDouble(formula.substring(0, opeIdx));
        double y = Double.parseDouble(formula.substring(opeIdx + 1,formula.length()));
 
        // 演算子に応じた計算結果をreturn
        double answer;
        if (ope == '+') {
            answer = x + y;
        } else if (ope == '-') {
            answer = x - y;
        } else if (ope == '*') {
            answer = x * y;
        } else {
            answer = x / y;
        }
        return answer;
    }
}