package com.itechart.app.controller.helpers;

import com.itechart.app.dao.TelephoneDAO;
import com.itechart.app.entity.Telephone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maxim on 12/16/2015.
 */
public class TelephoneHelper extends AbstractHelper {

    final Logger LOGGER = LoggerFactory.getLogger(TelephoneHelper.class);

    public static final TelephoneHelper INSTANCE = new TelephoneHelper();

    private TelephoneHelper(){}

    public void processTelephones(HttpServletRequest request,Integer contactId){
        LOGGER.debug("editing telephones");
        ArrayList<Integer> tel_id = getIntListFromArray(request.getParameterValues("tel_id"));
        String[] tel_country_code = request.getParameterValues("tel_country_code");
        String[] tel_operator_code = request.getParameterValues("tel_operator_code");
        String[] tel_number = request.getParameterValues("tel_number");
        String[] tel_type = request.getParameterValues("tel_type");
        String[] tel_comment = request.getParameterValues("tel_comment");
        HashMap<Integer,Telephone> telephoneHashMap = new HashMap<Integer, Telephone>();
        for(int i=0;i<tel_id.size();i++)
            telephoneHashMap.put(tel_id.get(i),new Telephone(tel_id.get(i),tel_country_code[i],tel_operator_code[i],tel_number[i],tel_type[i],tel_comment[i],contactId));


        ArrayList<Telephone> newTelephonesArray = getTelephonesByCriterionFromRequest("newTelephones", request, telephoneHashMap);
        ArrayList<Telephone> updatedTelephonesArray = getTelephonesByCriterionFromRequest("updatedTelephones", request, telephoneHashMap);
        deleteTelephones(getIntListFromArray(request.getParameterValues("deletedTelephones")));
        createTelephones(newTelephonesArray);
        updateTelephones(updatedTelephonesArray);

    }

    private void deleteTelephones(ArrayList<Integer> telephone_ids){
        LOGGER.debug("deleting telephones");
        for(Integer id : telephone_ids)
            TelephoneDAO.INSTANCE.delete(id);
    }
    private void updateTelephones(ArrayList<Telephone> telephones){
        LOGGER.debug("updating telephones");
        for(Telephone t : telephones)
            TelephoneDAO.INSTANCE.update(t);
    }
    private void createTelephones(ArrayList<Telephone> telephones){
        LOGGER.debug("creating telephones");
        Integer i = TelephoneDAO.INSTANCE.maxRow();
        i++;
        for(Telephone t : telephones)
        {
            t.setId(i);
            TelephoneDAO.INSTANCE.create(t);
            i++;
        }
    }


    private ArrayList<Telephone> getTelephonesByCriterionFromRequest(String criterion,HttpServletRequest request, HashMap<Integer,Telephone> telephoneHashMap){
        return getSpecificTelephonesArray(getIntListFromArray(request.getParameterValues(criterion)),telephoneHashMap);
    }

    private ArrayList<Telephone> getSpecificTelephonesArray(ArrayList<Integer> ids, HashMap<Integer,Telephone> telephoneHashMap){
        ArrayList<Telephone> specificArray = new ArrayList<Telephone>();
        Telephone telephone = null;
        for(Integer id : ids){
            telephone = telephoneHashMap.get(id);
            if(telephone!=null)specificArray.add(telephone);
        }
        return specificArray;
    }
}
