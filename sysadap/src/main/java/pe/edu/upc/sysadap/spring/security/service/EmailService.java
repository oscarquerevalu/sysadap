package pe.edu.upc.sysadap.spring.security.service;

import pe.edu.upc.sysadap.spring.security.model.Mail;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email/email-template", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void sendEmail() {
        try {
        	Mail mail = new Mail();
            mail.setFrom("no-reply@sistemadaptativo.com");
            mail.setTo("oscarquerevalu@gmail.com");
            mail.setSubject("Reporte Inteligencias Multiples");

            Map<String, Object> model = new HashMap<>();
            model.put("token", "");
            model.put("user", "");
            model.put("signature", "https://sistemadaptativo.com");
            mail.setModel(model);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            context.setVariable("imageResourceName", "img1.jpg");
            context.setVariable("imageResourceName2", "img2.jpg");
            String html = templateEngine.process("email/email-template2", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            
            final String fiat = "FIAT";
            final String audi = "AUDI";
            final String ford = "FORD";
            final String speed = "Speed";
            final String millage = "Millage";
            final String userrating = "User Rating";
            final String safety = "safety";

            final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            dataset.addValue( 1.0 , fiat , speed );
            dataset.addValue( 3.0 , fiat , userrating );
            dataset.addValue( 5.0 , fiat , millage );
            dataset.addValue( 5.0 , fiat , safety );

            dataset.addValue( 5.0 , audi , speed );
            dataset.addValue( 6.0 , audi , userrating );
            dataset.addValue( 10.0 , audi , millage );
            dataset.addValue( 4.0 , audi , safety );

            dataset.addValue( 4.0 , ford , speed );
            dataset.addValue( 2.0 , ford , userrating );
            dataset.addValue( 3.0 , ford , millage );
            dataset.addValue( 6.0 , ford , safety );

//            JFreeChart barChart = ChartFactory.createPieChart(
//               "SISTEMA ADAPTATIVO PREDICTIVO", 
//               "Category", "Score", 
//               dataset,PlotOrientation.VERTICAL, 
//               true, true, false);
            
            JFreeChart pieChart = ChartFactory.createPieChart3D
            		("SISTEMA ADAPTATIVO PREDICTIVO", createDataset(new Double[]{1.1,2.2,3.3,4.4,5.5,6.6,7.7,8.8}), true, true, true);
               
            int width = 640;    /* Width of the image */
            int height = 480;   /* Height of the image */ 
            File BarChart = new File( "BarChart.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( BarChart , pieChart , width , height );
            Slice s1 = Slice.newSlice(30, Color.newColor("CACACA"), "Safari", "Apple"); 
            Slice s2 = Slice.newSlice(30, Color.newColor("DF7417"), "Firefox", "Mozilla"); 
            Slice s3 = Slice.newSlice(30, Color.newColor("951800"), "Chrome", "Google"); 
            Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Internet Explorer", "Microsoft"); 
     
            PieChart chart = GCharts.newPieChart(s1, s2, s3, s4); 
            chart.setTitle("SISTEMA ADAPTATIVO PREDICTIVO", Color.newColor("01A1DB"), 16); 
            chart.setSize(500, 200); 
            chart.setThreeD(true); 
            String url = chart.toURLString(); 
            URL url2 = new URL(url);
            Resource res = new InputStreamResource(url2.openStream());
            helper.addInline("img1.jpg", BarChart);
            BufferedImage img = ImageIO.read(url2);
            File file = new File("downloaded.jpg");
            ImageIO.write(img, "jpg", file);
            helper.addInline("img2.jpg", file);
//            helper.addAttachment("MyImageName.jpg", new ByteArrayResource(value.getBytes()));

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    private DefaultPieDataset createDataset(Double[] valores) {
    	DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("LINGÜÍSTICO- VERBAL", valores[0]);
        dataset.setValue("LÓGICA MATEMÁTICA", valores[1]);
        dataset.setValue("ESPACIAL", valores[2]);
        dataset.setValue("CORPORAL KINESTÉSICA", valores[3]);
        dataset.setValue("MUSICAL", valores[4]);
        dataset.setValue("INTERPERSONAL", valores[5]);
        dataset.setValue("INTRAPERSONAL", valores[6]);
        dataset.setValue("NATURALISTA", valores[7]);
        return dataset;
    }

}
