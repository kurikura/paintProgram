/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kurikura
 */
public class dbaccess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection db_con=null;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con=DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","kurikura","mimoriko");
                out.println("接続しました");
                Statement statement=db_con.createStatement();
                String sql="SELECT * FROM profiles where profilesID=1;";
                ResultSet rs =statement.executeQuery(sql);
                while(rs.next()){
                    int profilesID=rs.getInt("profilesID");
                    String name=rs.getString("name");
                    String tell=rs.getString("tell");
                    int age=rs.getInt("age");
                    Date birthday=rs.getDate("birthday");
                    out.println("<p>");
                    out.println("profilesID:"+profilesID);
                    out.println(" name:"+name);
                    out.println(" tell:"+tell);
                    out.println(" age:"+age);
                    out.println(" birthday:"+birthday);
                    out.println("</p>");
                }
                db_con.close();
            }catch(SQLException sqle){
                out.println("接続時にエラーが発生しました。"+sqle.toString());
            }catch(ClassNotFoundException cnfe){
                out.println("接続時にエラーが発生しました。"+cnfe.toString());
            }catch(Exception e){
                out.println("接続時にエラーが発生しました。"+e.toString());
            }finally{
                if(db_con!=null){
                    try{
                        db_con.close();
                    }catch(Exception e_con){
                        System.out.println(e_con.getMessage());
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
