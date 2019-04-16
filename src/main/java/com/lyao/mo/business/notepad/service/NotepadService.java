package com.lyao.mo.business.notepad.service;

import com.lyao.mo.business.notepad.bean.po.T_notepad;

import java.util.List;

/**
 * @author lyao
 * @date 2019/4/16 14:51
 * @description
 */
public interface NotepadService {

    /**
     * 查询全部便签
     * @param customerId
     * @return
     */
    List<T_notepad> selectNotepads(String customerId);

    /**
     *查询某个便签
     * @param notepadId
     * @return
     */
    T_notepad selectNotepadById(String notepadId);

    /**
     * 新增一个便签
     * @param notepad
     * @return
     */
    boolean addNotepad(T_notepad notepad);

    /**
     * 删除一个便签
     * @param id
     * @return
     */
    boolean deleteNotePadById(String id);

    /**
     * 修改便签名称
     * @param id
     * @param name
     * @return
     */
    boolean updateNotepadName(String id, String name);

    /**
     * 将便签置顶
     * @param id
     * @return
     */
    boolean updateNotepadTops(String customerId, String id);

    /**
     * 修改便签内容
     * @param id
     * @param content
     * @return
     */
    boolean updateNotepadContent(String id, String content);
}
