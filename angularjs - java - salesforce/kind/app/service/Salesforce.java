package service;

import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import lib.Toolbox;
import models.Respondent;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Salesforce {

    private static ConnectionManager ref;
    private static PartnerConnection connection;
    private static ConnectorConfig config;
    private static final String username = "artyoung@gmail.com";
    private static final String password = "123SalesForce@";
    private static final String securityToken = "7c7H2mYG9q2C1jwwl1OaBhNU";
    private static final String SESSION_KEY = "sessionKey";
    private static final String SESSION_VALUE = "sessionValue";

    public static void SubmitToSalesforce(Respondent r, Response[] responses)
    {
        String contactId = FindContactIdByEmail(r.email);
        if(contactId.equals(""))
        {
            Toolbox.Log("Creating contact...");
            contactId = CreateContact(r);
        }
        String campaignId = FindCampaignIdByName("Kindness Study");
        Toolbox.Log("contactId: " + contactId);
        Toolbox.Log("campaignId: " + campaignId);

        String responseData = "";
        responseData += RenderField("Name", r.firstName + " " + r.lastName);
        responseData += RenderField("Email", r.email);
        responseData += RenderField("City", r.city);
        responseData += RenderField("Age", r.age);
        responseData += RenderField("Gender", r.gender);

        for(int i = 0; i < responses.length; i++)
        {
            responseData += RenderField(responses[i].question, responses[i].response);
        }

        if(contactId.equals("") || campaignId.equals(""))
        {
        }
        else
        {
            CreateCampaignMember(campaignId,  contactId);
            CreateKindnessStudyResponse(campaignId,  contactId, responseData, r);
        }
    }

    private static String RenderField(String label, String value)
    {
        return label + ":" + value + "\r\n\r\n";
    }

    public static String FindContactIdByEmail(String email)
    {
        QueryResult qResult = null;
        try {
            String soqlQuery = "select Id from Contact where Email='" + email.toString() + "'";

            PartnerConnection connection = GetConnection();
            qResult = connection.query(soqlQuery);
            boolean done = false;
            if (qResult.getSize() > 0) {
                SObject[] records = qResult.getRecords();
                return records[0].getId();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Toolbox.Log("failed to find contact");

        return "";
    }

    public static String FindCampaignIdByName(String campaignName)
    {
        QueryResult qResult = null;
        try {
            String sql = "SELECT Id FROM Campaign where name='"+campaignName+"'";

            PartnerConnection connection = GetConnection();
            qResult = connection.query(sql);
            if (qResult.getSize() > 0) {
                System.out.println("Logged-in user can see a total of " + qResult.getSize() + " contact records.");
                SObject[] records = qResult.getRecords();
                return records[0].getId();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Toolbox.Log("failed to find campaign " + campaignName);

        return "";
    }

    public static String CreateKindnessStudyResponse(String campaignId,  String contactId, String responseData, Respondent r)
    {
        String idResult = "";
        Date date = new Date();
        String title = r.firstName + " " + r.firstName + " - " + r.email + " - " + date.toString();
        try {
            // populate the new opportunity
            SObject obj = new SObject();
            obj.setType("Kindness_Study_Response__c");
            obj.setField("CampaignId__c", campaignId);
            obj.setField("ContactId__c", contactId);
            obj.setField("ResponseData__c", responseData);
            obj.setField("Name", title);

            // add the opportunity sobject to the array for the create command
            SObject[] objs = {obj};

            // get a reference to the connection
            PartnerConnection connection = GetConnection(); //  ConnectionManager.getConnectionManager().getConnection();
            if(connection != null)
            {
                idResult = GetIdFromResult(connection.create(objs));
            }
            Toolbox.Log("successfully added Kindness_Study_Response");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return idResult;
    }

    public static boolean CreateCampaignMember(String campaignId, String contactId)
    {
        String idResult = "";
        boolean success = false;

        try {

            String sql = "SELECT CampaignId FROM CampaignMember where CampaignId='"+campaignId+"' and ContactId='"+contactId+"'";

            PartnerConnection connection = GetConnection();
            QueryResult qResult = null;
            qResult = connection.query(sql);
            if (qResult.getSize() > 0) {
                Toolbox.Log("Campaign Member already exists");
                return false;
            }

            Toolbox.Log("adding campaign member: " + campaignId + ", " + contactId);


            // populate the new opportunity
            SObject obj = new SObject();
            obj.setType("CampaignMember");
            obj.setField("CampaignId", campaignId);
            obj.setField("ContactId", contactId);
            /*
            Date dt = new Date();
            obj.setField("FirstRespondedDate", dt);
            obj.setField("HasResponded", true);
              */

            // add the opportunity sobject to the array for the create command
            SObject[] objs = {obj};

            idResult = GetIdFromResult(connection.create(objs));

            Toolbox.Log("successfully added member to campaign");
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return success;
    }

    public static String CreateContact(Respondent r)
    {
        String idResult = "";

        try {
            // populate the new opportunity
            SObject obj = new SObject();
            obj.setType("Contact");
            obj.setField("FirstName", r.firstName);
            obj.setField("LastName", r.lastName);
            obj.setField("MailingCity", r.city);
            obj.setField("Email", r.email);

            // add the opportunity sobject to the array for the create command
            SObject[] objs = {obj};

            // get a reference to the connection
            PartnerConnection connection = GetConnection(); //  ConnectionManager.getConnectionManager().getConnection();
            if(connection != null)
            {
                SaveResult result[] = connection.create(objs);
                if(result.length > 0)
                {
                    idResult = result[0].getId().toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idResult;
    }

    private static String GetIdFromResult(SaveResult[] result)
    {
        if(result.length > 0)
        {
            result[0].getId().toString();
        }

        return "";
    }

    private static PartnerConnection _Connection = null;
    private static PartnerConnection GetConnection() {

        if(_Connection == null)
        {
            _Connection = CreateConnection();
        }
        return _Connection;
    }

    private static PartnerConnection CreateConnection()
    {
        try {

            config = new ConnectorConfig();
            config.setUsername(username);
            config.setPassword(password + securityToken);
            config.setTraceMessage(true);

            connection = Connector.newConnection(config);

            System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
            System.out.println("Service EndPoint: "+config.getServiceEndpoint());
            System.out.println("Username: "+config.getUsername());
            System.out.println("SessionId: "+config.getSessionId());


        } catch ( ConnectionException ce) {
            System.out.println("ConnectionException " +ce.getMessage());
        }

        if(null == connection)
        {
            Toolbox.Log("no connection");
        }

        //System.out.println("===========> SessionId...."+config.getSessionId());

        return connection;
    }
}
