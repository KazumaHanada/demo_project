package com.example.demo;

import com.example.demo.AccountTbl.Account;
import com.example.demo.AccountTbl.AccountMapper;
import com.example.demo.CustomerTbl.Customer;
import com.example.demo.CustomerTbl.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class DemoController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CustomerMapper customerMapper;



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



    //maint表示画面
    @RequestMapping(value = "/maint", method = RequestMethod.GET)
    public ModelAndView maint(ModelAndView mav, Model model){

        model.addAttribute("customerForm", new CustomerForm());

        List<Customer> cLst = customerMapper.selectAll();
        mav.addObject("cLst", cLst);
        mav.setViewName("maint");
        return mav;
    }

    //maint表示画面
    /*
    @RequestMapping(value = "/maint", method = RequestMethod.POST)
    public ModelAndView maint2(ModelAndView mav, Model model){

        mav.setViewName("maint");
        return mav;
    }
    */


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(ModelAndView mav,
                                  RedirectAttributes rav,
                                  @Validated @ModelAttribute CustomerForm form,
                                  BindingResult result){

        if(result.hasErrors()){
            rav.addFlashAttribute("msg", "未入力の項目があります");
            mav.setViewName("redirect:maint");
            return mav;
        }

        Customer costomer = new Customer();
        costomer.setCompany(form.getCompany());
        costomer.setDept(form.getDept());
        costomer.setPost(form.getPost());
        costomer.setName(form.getName());
        costomer.setC_dept(form.getC_dept());
        costomer.setC_post(form.getC_post());
        costomer.setC_name(form.getC_name());
        customerMapper.insert(costomer);

        mav.setViewName("redirect:maint");
        return mav;

    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete (ModelAndView mav,
                                @RequestParam("id")int id) {

        customerMapper.delete(id);

        mav.setViewName("redirect:maint");
        return mav;
    }

}