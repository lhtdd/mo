package com.lyao.mo.business.notepad.service.implement;

import com.lyao.mo.business.notepad.bean.po.T_notepad;
import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.business.notepad.service.NotepadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        T_notepad notepadOfTops = commonBaseDao.selectOne("notepad.getTopsNotepad", customerId);
        List<T_notepad> notepads = commonBaseDao.selectList("notepad.getNotepads", customerId);
        notepads.add(0,notepadOfTops);
        return notepads;
    }

    @Override
    public T_notepad selectNotepadById(String notepadId) {
        return commonBaseDao.selectOne("notepad.getNotepadById", notepadId);
    }

    @Override
    public boolean addNotepad(T_notepad notepad) {
        int i = commonBaseDao.insert("notepad.addNotepad", notepad);
        if (i == 1){
            return true;
        }
        return false;
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
        if (i+j == 2){
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
}
