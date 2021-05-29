package com.example.sdorica.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sdorica.service.VisitCounter;
import com.example.sdorica.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sdorica.domain.Character;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterService;

    //添加角色
    @ResponseBody
    @RequestMapping(value = "/character/add", method = RequestMethod.POST)
    public Object addCharacter(HttpServletRequest req) {
        JSONObject object = new JSONObject();
        String name = req.getParameter("name").trim();
        String label = req.getParameter("label").trim();
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
        if (name.equals("") || position.equals("")) {
            object.put("code", 0);
            object.put("msg", "名字或站位为空");
            return object;
        }
        //名字重复
        List<Character> list = characterService.characterOfName(name);
        if (list.size() > 0) {
            object.put("code", 0);
            object.put("msg", "已存在该角色");
            return object;
        }

        Character character = new Character();
        character.setName(name);
        character.setLabel(label);
        character.setPosition(Integer.parseInt(position));
        character.setHave(Integer.parseInt(have));
        if (skill_1.equals("")) {
            character.setSkill1(null);
        } else {
            character.setSkill1(skill_1);
        }
        if (skill_2.equals("")) {
            character.setSkill2(null);
        } else {
            character.setSkill2(skill_2);
        }
        if (skill_3.equals("")) {
            character.setSkill3(null);
        } else {
            character.setSkill3(skill_3);
        }
        if (skill_4.equals("")) {
            character.setSkill4(null);
        } else {
            character.setSkill4(skill_4);
        }
        if (skill_6.equals("")) {
            character.setSkill6(null);
        } else {
            character.setSkill6(skill_6);
        }
        if (skill_p.equals("")) {
            character.setSkillP(null);
        } else {
            character.setSkillP(skill_p);
        }
        if (skill_a.equals("")) {
            character.setSkillA(null);
        } else {
            character.setSkillA(skill_a);
        }

        boolean res = characterService.addCharacter(character);
        if (res) {
            object.put("code", 1);
            object.put("msg", "添加成功");
        } else {
            object.put("code", 0);
            object.put("msg", "添加失败");
        }
        return object;
    }

    //更新角色信息
    @RequestMapping(value = "/character/update", method = RequestMethod.POST)
    public Object updateCharacter(HttpServletRequest req) {
        JSONObject object = new JSONObject();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name").trim();
        String label = req.getParameter("label").trim();
        String position = req.getParameter("position");
        String have = req.getParameter("have");
        String skill_1 = req.getParameter("skill_1");
        String skill_2 = req.getParameter("skill_2");
        String skill_3 = req.getParameter("skill_3");
        String skill_4 = req.getParameter("skill_4");
        String skill_6 = req.getParameter("skill_6");
        String skill_p = req.getParameter("skill_p");
        String skill_a = req.getParameter("skill_a");

        //id不存在
        Character list = characterService.characterOfId(id);
        if (list == null) {
            object.put("code", 0);
            object.put("msg", "id无效");
            return object;
        }

        Character character = new Character();
        character.setId(id);
        character.setName(name);
        character.setLabel(label);
        character.setPosition(Integer.parseInt(position));
        character.setHave(Integer.parseInt(have));
        character.setSkill1(skill_1);
        character.setSkill2(skill_2);
        character.setSkill3(skill_3);
        character.setSkill4(skill_4);
        character.setSkill6(skill_6);
        character.setSkillP(skill_p);
        character.setSkillA(skill_a);

        boolean res = characterService.updateCharacter(character);
        if (res) {
            object.put("code", 1);
            object.put("msg", "修改成功");
        } else {
            object.put("code", 0);
            object.put("msg", "修改失败");
        }

        return object;
    }

    //返回所有角色
//    @ResponseBody
    @RequestMapping(value = "/character", method = RequestMethod.GET)
    public Object allCharacter() {
        VisitCounter visitCounter = VisitCounter.getVisitCounter();
        visitCounter.show();
        return characterService.allCharacter();
    }

    //根据名字查找角色
    @RequestMapping(value = "character/name/detail", method = RequestMethod.GET)
    public Object characterOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();
        VisitCounter visitCounter = VisitCounter.getVisitCounter();
        visitCounter.show();
        return characterService.characterOfName(name);
    }

    //根据站位查找角色
    @RequestMapping(value = "character/position/detail", method = RequestMethod.GET)
    public Object characterOfPosition(HttpServletRequest req) {
        String position = req.getParameter("position").trim();
        return characterService.characterOfPosition(position);
    }

    //根据id查询角色
    @RequestMapping(value = "character/id/detail", method = RequestMethod.GET)
    public Object characterOfId(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        VisitCounter visitCounter = VisitCounter.getVisitCounter();
        visitCounter.show();
        return characterService.characterOfId(Integer.parseInt(id));
    }

    //关键词查询
    @RequestMapping(value = "search/detail", method = RequestMethod.GET)
    public Object characterOfSearch(HttpServletRequest req) {
        String from = req.getParameter("from").trim();
        String keyword = req.getParameter("keyword").trim();
        VisitCounter visitCounter = VisitCounter.getVisitCounter();
        visitCounter.show();
        return characterService.characterOfSearch(from, keyword);
    }

    //综合查询
    @RequestMapping(value = "filter/detail", method = RequestMethod.GET)
    public Object characterOfFilter(HttpServletRequest req) {
        String have;
        String position;
        String number;
        String type;
        String haveSelect = req.getParameter("have").trim();
        if (haveSelect.equals("true"))have="1";
        else if(haveSelect.equals("false"))have="0";
        else have=haveSelect;
        String positionSelect = req.getParameter("position").trim();
        switch (positionSelect){
            case "white":position="1";break;
            case "black":position="2";break;
            case "gold":position="3";break;
            default:position="all";
        }
        String numberSelect = req.getParameter("number").trim();
        if (numberSelect.equals("all"))number=numberSelect;
        else number="skill_"+numberSelect;
        type = req.getParameter("type").trim();
        System.out.println("have:"+haveSelect+",position:"+positionSelect+",number:"+number+",type:"+type);

        VisitCounter visitCounter = VisitCounter.getVisitCounter();
        visitCounter.show();
        return characterService.characterOfFilter(have, position, number, type);
    }

}
