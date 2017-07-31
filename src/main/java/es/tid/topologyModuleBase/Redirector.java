package es.tid.topologyModuleBase;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import es.tid.topologyModuleBase.TopologyModuleParams;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author b.angv
 *
 */

public class Redirector extends HttpServlet  {

    public void Redirect(){
      try{
        TopologyModuleParams littleParams = new TopologyModuleParams();
        System.out.println(littleParams.getExportTAPIPort());
        Server server = new Server(9090);
        server.setHandler(new PortRedirector(8089));
        server.start();
        server.dumpStdErr();
        server.join();
      }catch(Exception e){
        System.out.println(e);
      }
    }

    public class PortRedirector extends AbstractHandler {

        int to;
        PortRedirector(int to) {
            this.to = to;
        }
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            String uri = request.getScheme() + "://" +
                    request.getServerName() +
                    ":" + to +
                    request.getRequestURI() + "/";

            //response.setStatus(HttpServletResponse.SC_OK);
            //response.sendRedirect(uri);
            URL obj = new URL(null, uri, new sun.net.www.protocol.http.Handler());

        		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        		//add reuqest header
        		con.setRequestMethod(request.getMethod());
            // add header
        		con.setRequestProperty("Content-Type", request.getHeader("Content-Type"));
            con.setRequestProperty("Accept", request.getHeader("Accept"));
            //TODO find if the methos is GET or POST and do the query
        		// Send post request
        		con.setDoOutput(true);
        		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        		wr.writeBytes(" ");
        		wr.flush();
        		wr.close();

        		int responseCode = con.getResponseCode();
        		System.out.println("\nSending " + request.getMethod() + " request to URL : " + uri);
        		System.out.println("Response Code : " + responseCode);

        		BufferedReader in = new BufferedReader(
        		        new InputStreamReader(con.getInputStream()));
        		String inputLine;
        		StringBuffer res = new StringBuffer();

        		while ((inputLine = in.readLine()) != null) {
        			res.append(inputLine);
        		}
        		in.close();


            response.getWriter().write(res.toString());
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
