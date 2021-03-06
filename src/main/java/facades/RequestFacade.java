/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Contact;
import entity.Department;
import entity.Invoice;
import entity.Request;
import entity.Resource;
import entityconnection.EntityConnector;
import enums.RequestStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dennisschmock
 */
public class RequestFacade {
    private static EventFacade ef = new EventFacade();

    public static void main(String[] args) {
        RequestFacade rf = new RequestFacade();
        Invoice invoice = new Invoice("Google", "55667788", "test", "Somestreet", "2222");
        Contact contact = new Contact("Hans Jensen", "44556677", "dennis@schmock.eu");
        Request request = new Request("Julefrokost", 400, "Børn", new Date(), "Vega", "Julevej", 1300, new Date(), new Date(), new Date(), new Date(), "Klares", true, "Test", true, true, true, true, true, "visi");
        request.setContact(contact);
        request.setInvoice(invoice);
        rf.createRequest(request);
    }

    public List<Request> getRequests() {
        List<Request> requests = null;
        EntityManager em = EntityConnector.getEntityManager();

        try {
            Query q = em.createQuery("SELECT r FROM Request r");
            requests = q.getResultList();
        } catch (Exception e) {
            log.Log.writeToLog("Error in getting Requests " + e.getMessage());
        } finally {
            em.close();
        }
        return requests;
    }

    public Request createRequest(Request request) {
        EntityManager em = EntityConnector.getEntityManager();

         if(request.getDepartment() == null){
             Department dept = em.find(Department.class, "København");
             request.setDepartment(dept);
         }
         if(request.getRequestStatus() == null){
             request.setRequestStatus(RequestStatus.RECIEVED);
         }
        try {
            em.getTransaction().begin();
            em.persist(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.Log.writeToLog("");
        } finally {
            em.close();
        }
        return request;
    }

    public Request updateRequest(Request request) {
        EntityManager em = EntityConnector.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(request);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return request;
    }

    public Request getRequest(int id) {
        EntityManager em = EntityConnector.getEntityManager();
        Request request;
        try {
            request = em.find(Request.class, id);
        } finally {
            em.close();
        }
        return request;
    }

    public List<Request> getRequests(Department d) {
        EntityManager em = EntityConnector.getEntityManager();
        Query q = em.createQuery("SELECT r FROM Request r where r.department.nameOfDepartment=:dept");
        q.setParameter("dept", d.getNameOfDepartment());
        List resultList = q.getResultList();
        return resultList;
    }

    public Request approveRequest(int id) {
        EntityManager em = EntityConnector.getEntityManager();
        Request r = null;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT r FROM Request r where r.id=:id");
            q.setParameter("id", id);
            r = (Request) q.getSingleResult();
            r.setRequestStatus(RequestStatus.APPROVED);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return r;
    }
    
    public Request rejectRequest(int id) {
        EntityManager em = EntityConnector.getEntityManager();
        Request r = null;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT r FROM Request r where r.id=:id");
            q.setParameter("id", id);
            r = (Request) q.getSingleResult();
            r.setRequestStatus(RequestStatus.REJECTED);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return r;
    }

    public List<Resource> getRequestResources(int requestId) {
        
        EntityManager em = EntityConnector.getEntityManager();
        Request r = null;
        try {
            Query q = em.createQuery("SELECT r FROM Request r where r.id=:id");
            q.setParameter("id", requestId);
            r = (Request) q.getSingleResult();
        }
        finally{
            em.close();
        }
        List<Resource> availableResources = ef.getAvailableResourcesForDates(r.getEventstart(), r.getEventend());
        
        return availableResources;
    }

}
