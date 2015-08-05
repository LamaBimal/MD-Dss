package controller.servlet;

import business.service.SetDiseaseContent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class proceed extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ArrayList<Integer> s_ids_piles = new ArrayList<>();
    ArrayList<Integer> s_ids_pneumonia = new ArrayList<>();
    ArrayList<Integer> s_ids_asthma = new ArrayList<>();

    int piles_id = 0;
    int pneumonia_id = 1;
    int asthma_id = 2;

    public proceed() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double prob1=0,prob2 = 0,prob3 = 0;
        String id[]= new String[26];
        for(int i=0;i<26;i++)
        {
            id[i]="null";
            String symptom="symptom"+i;
            if(request.getParameter(symptom)!=null)
            {
              id[i]=request.getParameter(symptom);
            }
        }
        
        String[] in_symp;
        //for symptom1
        for(int i=0;i<26;i++)
        {
            if(!(id[i].equals("null")))
            {
                in_symp = id[i].split(" ");
                System.out.println("The selected item is "+id[i]);
                System.out.println("The disease_id is "+ in_symp[0]);
               // System.out.println("The symptoms_id is "+in_symp[1]);
                if (Integer.parseInt(in_symp[0]) == piles_id) {
                     s_ids_piles.add(Integer.parseInt(in_symp[1]));
                    } else if (Integer.parseInt(in_symp[0]) == asthma_id) {
                 s_ids_asthma.add(Integer.parseInt(in_symp[1]));
            } else if (Integer.parseInt(in_symp[0]) == pneumonia_id) {
                s_ids_pneumonia.add(Integer.parseInt(in_symp[1]));
            }

            }
        }
        
        if(!(s_ids_piles.isEmpty()))
        {
            System.out.println("**************************************************************************");
            SetDiseaseContent gdc1 = new SetDiseaseContent(piles_id, s_ids_piles);
            prob1 = gdc1.get_probability();
           System.out.println("The probability of piles is "+prob1);
         }
        
        
       if(!(s_ids_pneumonia.isEmpty()))
        {
            System.out.println("**************************************************************************");

         SetDiseaseContent gdc2 = new SetDiseaseContent(pneumonia_id, s_ids_pneumonia);
         prob2 = gdc2.get_probability();
         System.out.println("The probability of pneumonia is "+prob2);
        }
        if(!(s_ids_asthma.isEmpty()))
        {
        System.out.println("**************************************************************************");
         SetDiseaseContent gdc3 = new SetDiseaseContent(asthma_id, s_ids_asthma);
        prob3 = gdc3.get_probability();
        System.out.println("The probability of asthma is "+prob3);
        System.out.println("**************************************************************************");
        }
        String result="The probability of piles is "+prob1+"<br/> The probability of pneumonia is "+prob2+"<br/> The probability of asthma is "+prob3;
        request.setAttribute("message", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("nextpage.jsp");
        dispatcher.forward(request, response);
        /*return;*/

    }

}
