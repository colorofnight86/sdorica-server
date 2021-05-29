package com.example.sdorica.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sdorica.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterService;

    @ResponseBody
    //添加角色
    @RequestMapping(value = "/character/add", method = RequestMethod.POST)
    public Object addCharacter(HttpServletRequest req) {
        JSONObject object = new JSONObject();
        String name = req.getParameter("name").trim();
        String position = req.getParameter("position");
        String have = req.getParameter("have");
        String skill_1 = req.getParameter("skill_1");
        String skill_2 = req.getParameter("skill_2");
        String skill_3 = req.getParameter("skill_3");
        String skill_4 = req.getParameter("skill_4");
        String skill_6 = req.getParameter("skill_6");
        String skill_p = req.getParameter("skill_p");
        String skill_a = req.getParameter("skill_a");

        //名字或站位为空
        if(name.equals("")||position.equals("")){
            object.put("code", 0);
            object.put("msg", "名字或站位为空");
            return object;
        }

        Character character = new Character();
        character.setName(name);
        character.setPosition(position);
        character.setHave(Integer.parseInt(have));
//        character.setHave(0);
        if(skill_1.equals("")){ character.setSkill1(null); }
        else { character.setSkill1(skill_1); }
        if(skill_2.equals("")){ character.setSkill2(null); }
        else { character.setSkill2(skill_2); }
        if(skill_3.equals("")){ character.setSkill3(null); }
        else { character.setSkill3(skill_3); }
        if(skill_4.equals("")){ character.setSkill4(null); }
        else { character.setSkill4(skill_4); }
        if(skill_6.equals("")){ character.setSkill6(null); }
        else { character.setSkill6(skill_6); }
        if(skill_p.equals("")){ character.setSkillP(null); }
        else { character.setSkillP(skill_p); }
        if(skill_a.equals("")){ character.setSkillA(null); }
        else { character.setSkillA(skill_a); }

        boolean res = characterService.addCharacter(character);
        if(res){
            object.put("code", 1);
            object.put("msg", "添加成功");
        }else{
            object.put("code", 0);
            object.put("msg", "添加失败");
        }
        return object;
    }

    //返回所有角色
    @RequestMapping(value = "/character", method = RequestMethod.GET)
    public Object allCharacter() {
        return characterService.allCharacter();
    }

    //根据名字查找角色
    @RequestMapping(value = "character/name/detail", method = RequestMethod.GET)
    public Object characterOfName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return characterService.characterOfName(name);
    }

    //根据站位查找角色
    @RequestMapping(value = "character/position/detail", method = RequestMethod.GET)
    public Object characterOfPosition(HttpServletRequest req){
        String position = req.getParameter("position").trim();
        return characterService.characterOfPosition(position);
    }

    //根据id查询角色
    @RequestMapping(value = "character/id/detail", method = RequestMethod.GET)
    public Object characterOfId(HttpServletRequest req){
        String id = req.getParameter("id").trim();
        return characterService.characterOfId(Integer.parseInt(id));
    }

    @RequestMapping(value = "search/detail", method = RequestMethod.GET)
    public Object characterOfSearch(HttpServletRequest req){
        String from = req.getParameter("from").trim();
        String keyword = req.getParameter("keyword").trim();
        return characterService.characterOfSearch(from, keyword);
    }

}
