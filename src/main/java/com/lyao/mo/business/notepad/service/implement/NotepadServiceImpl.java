package com.lyao.mo.business.notepad.service.implement;

import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.business.notepad.bean.po.T_notepad;
import com.lyao.mo.business.notepad.service.NotepadService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyao
 * @date 2019/4/16 14:53
 * @description
 */
@Service("notepadService")
public class NotepadServiceImpl implements NotepadService {

    private final Logger log = Logger.getLogger(NotepadServiceImpl.class);

    @Autowired
    public CommonBaseDao commonBaseDao;

    @Override
    public List<T_notepad> selectNotepads(String customerId) {
        List<T_notepad> notepads = commonBaseDao.selectList("notepad.getNotepads", customerId);
        if (notepads != null && notepads.size() > 0) {
            T_notepad notepadFirst = notepads.get(0);
            T_notepad notepad = commonBaseDao.selectOne("notepad.getNotepadById", notepadFirst.getId());
            notepads.remove(notepadFirst);
            notepads.add(0, notepad);
        }
        return notepads;
    }

    @Override
    public T_notepad selectNotepadById(String notepadId) {
        return commonBaseDao.selectOne("notepad.getNotepadById", notepadId);
    }

    @Override
    public T_notepad addNotepad(String customerId) {
        List<String> notepadNameNumbers = commonBaseDao.selectList("notepad.getNotepadNameNumbers", customerId);
        String notepadName = "便签";
        if (notepadNameNumbers != null && notepadNameNumbers.size() > 0){
            Integer curNumer = this.getMaxNameNumber(notepadNameNumbers);
            notepadName = notepadName + String.valueOf(curNumer+1);
        }
        T_notepad notepad = new T_notepad();
        notepad.setNotepadName(notepadName);
        notepad.setCustomerId(customerId);
        Integer id = this.addNotepad(notepad);
        return this.selectNotepadById(id.toString());
    }

    private Integer getMaxNameNumber(List<String> notepadNameNumbers){
        List<Integer> validNumbers = new ArrayList<Integer>();
        for (String nameNumber : notepadNameNumbers) {
            String reg = "^[-\\+]?[\\d]*$";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(nameNumber);
            boolean result = m.matches();
            if (result && StringUtils.isNotEmpty(nameNumber)) {
                validNumbers.add(Integer.valueOf(nameNumber));
            }
            if (validNumbers.size() == 0) {
                validNumbers.add(0);
            }
        }
        return Collections.max(validNumbers);
    }

    @Override
    public Integer addNotepad(T_notepad notepad) {
        commonBaseDao.insert("notepad.addNotepad", notepad);
        return Integer.valueOf(notepad.getId());
    }

    @Override
    public boolean deleteNotePadById(String id) {
        int i = commonBaseDao.delete("notepad.deleteNotepad", id);
        if (i == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNotepadName(String id, String name) {
        Map<String, Object> noteMap = new HashMap<String, Object>(3);
        noteMap.put("id", id);
        noteMap.put("notepadName", name);
        int i = commonBaseDao.update("notepad.updateNotepadName", noteMap);
        if (i == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNotepadTops(String customerId, String id) {
        int j = commonBaseDao.update("notepad.updateNoneTopsOfNotepad", customerId);
        int i = commonBaseDao.update("notepad.updateTopsOfNotepad", id);
        if (i+j >= 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNotepadContent(String id, String content) {
        Map<String, Object> noteMap = new HashMap<String, Object>(3);
        noteMap.put("id", id);
        noteMap.put("content", content);
        int i = commonBaseDao.update("notepad.updateNotepadContent", noteMap);
        if (i == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNameOrContent(String id, String name, String content) {
        Map<String, Object> noteMap = new HashMap<String, Object>(5);
        noteMap.put("id", id);
        noteMap.put("notepadName", name);
        noteMap.put("content", content);
        int i = commonBaseDao.update("notepad.updateNameOrContent", noteMap);
        if (i == 1){
            return true;
        }
        return false;
    }
}
