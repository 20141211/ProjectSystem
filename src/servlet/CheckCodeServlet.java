package servlet;
import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;

import java.awt.*;

import java.awt.image.*;

import javax.imageio.ImageIO;

public class CheckCodeServlet extends HttpServlet

{
       private static int WIDTH = 109;
       private static int HEIGHT = 40;
       public void doGet(HttpServletRequest request,HttpServletResponse response) 

                     throws ServletException,IOException

       {            
    	   	  //System.out.println(1230456);
              HttpSession session = request.getSession();
              response.setContentType("image/jpeg");
              ServletOutputStream sos = response.getOutputStream();
              System.out.println("--------------->");
              //�����������Ҫ�����ͼƬ
              response.setHeader("Pragma","No-cache");
              response.setHeader("Cache-Control","no-cache");
              response.setDateHeader("Expires", 0);

              
              //�����ڴ�ͼ�󲢻����ͼ��������
              BufferedImage image = 
                     new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
              Graphics g = image.getGraphics();

              
              //����������֤��
              char [] rands = generateCheckCode();
              

              //����ͼ��
              drawBackground(g);
              drawRands(g,rands);

              
              //����ͼ��Ļ��ƹ�̣����ͼ��
              g.dispose();
              

              //��ͼ��������ͻ���
              ByteArrayOutputStream bos = new ByteArrayOutputStream();
              ImageIO.write(image, "JPEG", bos);
              byte [] buf = bos.toByteArray();
              response.setContentLength(buf.length);

              //��������Ҳ��д�ɣ�bos.writeTo(sos);
              sos.write(buf);
              bos.close();
              sos.close();

 

              //����ǰ��֤����뵽Session��
              session.setAttribute("checkCode",new String(rands));

              //ֱ��ʹ������Ĵ��뽫�����⣬Session����������ύ��Ӧǰ���

              //request.getSession().setAttribute("check_code",new String(rands));
 
       }

       
       private char [] generateCheckCode()

       {

              //������֤����ַ��
              String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
              char [] rands = new char[4];
              for(int i=0; i<4; i++)
              {
                     int rand = (int)(Math.random() * 36);
                     rands[i] = chars.charAt(rand);
              }
              return rands;
       }

       

       private void drawRands(Graphics g , char [] rands)
       {
              g.setColor(Color.BLACK);
              g.setFont(new Font(null,Font.ITALIC|Font.BOLD,30));
              //�ڲ�ͬ�ĸ߶��������֤���ÿ���ַ�         
              g.drawString("" + rands[0],11,27);
              g.drawString("" + rands[1],36,25);
              g.drawString("" + rands[2],61,28);
              g.drawString("" + rands[3],86,26);
             // System.out.println(rands);
       }

       

       private void drawBackground(Graphics g)
       {
             //������
              g.setColor(new Color(0xDCDCDC));
              g.fillRect(0, 0, WIDTH, HEIGHT);
              //������120�����ŵ�
              for(int i=0; i<120; i++)
              {
                     int x = (int)(Math.random() * WIDTH);
                     int y = (int)(Math.random() * HEIGHT);
                     int red = (int)(Math.random() * 255);
                     int green = (int)(Math.random() * 255);
                     int blue = (int)(Math.random() * 255);
                     g.setColor(new Color(red,green,blue));        
                     g.drawOval(x,y,1,0);
              }
       }
}
