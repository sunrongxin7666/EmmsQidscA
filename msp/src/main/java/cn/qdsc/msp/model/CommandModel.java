package cn.qdsc.msp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.qdsc.msp.core.mdm.MDMService;

/**Command Model
 * Created by Sun Rx on 2016-10-25.
 */
public class CommandModel {
    interface RequestTypeString{
        String DeviceLock = "DeviceLock";//	锁定设备
        String  UPDATE_PASSCODE_AND_DEVICE_LOCK="UpdatePasscodeAndDeviceLock";

        String  DEVICE_INFORMATION="DeviceInformation";	// 获取设备信息
        String  ERASE_DEVICE="EraseDevice";	//	抹掉设备
        String  ERASE_ENTERPRISE_DATA="EraseEnterpriseData";	//	擦除企业数据，删除EMM客户端的文档、消息
        String  ERASE_ALL_DATA="EraseAllData";	//	擦除全部数据，删除EMM客户端的文档、消息，删除EMM管理的APP
    }

    private String commandUUID;
    private String requestType;
    private int cmdCode;
    private HashMap<String,String> commandMap = new HashMap<>();

    private String commandUUIDTag = "command_uuid";
    private String commandTag = "command";
    private String requestTypeTag = "request_type";

    public CommandModel(String cmdString) throws JSONException {
        JSONObject jsonObject = new JSONObject(cmdString);
        commandUUID = jsonObject.getString(commandUUIDTag);
        String cmdMapString = jsonObject.getString(commandTag);
        if(!cmdMapString.equals("")) {
            JSONObject cmmdMapJO = new JSONObject(cmdMapString);
            commandMap = parseJSONObjectToMap(cmmdMapJO);
            requestType = commandMap.get(requestTypeTag);
            parseRequestTypeToCmdCode(requestType);
        } else {
            throw new JSONException("Command Content Error");
        }
    }

    public CommandModel(JSONObject jsonObject) throws JSONException {
        commandUUID = jsonObject.getString(commandUUIDTag);
        String cmdMapString = jsonObject.getString(commandTag);
        if(!cmdMapString.equals("")) {
            JSONObject cmmdMapJO = new JSONObject(cmdMapString);
            commandMap = parseJSONObjectToMap(cmmdMapJO);
            requestType = commandMap.get(requestTypeTag);
            parseRequestTypeToCmdCode(requestType);
        } else {
            throw new JSONException("Command Content Error");
        }
    }
    private HashMap<String,String> parseJSONObjectToMap(JSONObject jsonObject) throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()){
            String key = it.next();
            String value = jsonObject.getString(key);
            map.put(key,value);
        }
        return map;
    }

    private void parseRequestTypeToCmdCode(String requestType){
        switch (requestType) {
            case RequestTypeString.DeviceLock: {
                cmdCode = MDMService.CommdCode.OP_LOCK;
                break;
            }
            case RequestTypeString.UPDATE_PASSCODE_AND_DEVICE_LOCK:{
                cmdCode = MDMService.CommdCode.OP_LOCK_KEY;
                break;
            }
            case RequestTypeString.DEVICE_INFORMATION:{
                cmdCode = MDMService.CommdCode.OP_REFRESH;
                break;
            }
            case RequestTypeString.ERASE_ALL_DATA:{
                cmdCode = MDMService.CommdCode.OP_FACTORY;
                break;
            }
            case RequestTypeString.ERASE_DEVICE:{
                cmdCode = MDMService.CommdCode.DELETE_DEVICE;
                break;
            }
            case RequestTypeString.ERASE_ENTERPRISE_DATA:{
                cmdCode =MDMService.CommdCode.OP_ERASE_CORP;
            }
        }
    }

    public String getCommandUUID(){
        return this.commandUUID;
    }

    public String getRequestType(){
        return requestType;
    }

    public HashMap<String,String>  getCommandMap(){
        return commandMap;
    }

    public int getCmdCode(){
        return cmdCode;
    }
}
