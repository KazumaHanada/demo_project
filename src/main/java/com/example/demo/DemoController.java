package com.example.demo;

import com.example.demo.AccountTbl.Account;
import com.example.demo.AccountTbl.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class DemoController {

    @Autowired
    private AccountMapper accountMapper;



    @RequestMapping("/")
    public String root(){

        return "redirect:/login";
    }



    //ログイン画面表示　
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mav, Model model){

        //login画面表示前に、使用するフォームクラスを作成するようにしないとエラー
        model.addAttribute("loginForm", new LoginForm());
        mav.setViewName("login");
        return mav;
    }

    //ログイン画面表示ログイン画面表示
    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login2(ModelAndView mav, Model model){

        //login画面表示前に、使用するフォームクラスを作成するようにしないとエラー
        model.addAttribute("loginForm", new LoginForm());
        mav.setViewName("login");
        return mav;
    }
    */


    //ログイン画面→top画面
    @RequestMapping(value = "/loginToTop", method = RequestMethod.POST)
    public ModelAndView loginToTop(ModelAndView mav,
                                    RedirectAttributes rav,
                                    @Validated @ModelAttribute LoginForm form,
                                    BindingResult result){


        if(result.hasErrors()){
            rav.addFlashAttribute("msg", "ユーザーネーム、または、パスワードが正しいかチェックしてください。");
            mav.setViewName("redirect:login");
            return mav;
        }


        List<Account> accountLst = accountMapper.findAccounts(form.getUserName(), form.getPassWord());
        if(accountLst.size() == 0 ){
            rav.addFlashAttribute("msg", "ユーザーネーム、または、パスワードが正しいかチェックしてください。");
            mav.setViewName("redirect:login");
            return mav;
        }


        mav.setViewName("redirect:top");
        return mav;
    }



    //トップ画面表示
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public String top(){

        return "top";
    }

    //トップ画面表示
    /*
    @RequestMapping(value = "/top", method = RequestMethod.POST)
    public String top2(){

        return "top";
    }
    */


    @RequestMapping(value = "/maint", method = RequestMethod.GET)
    public String maint(){

        return "maint";
    }



}