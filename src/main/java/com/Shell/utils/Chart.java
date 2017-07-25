package com.Shell.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;  
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.DateAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  
import org.jfree.data.xy.XYDataset;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.Data;   
  

public class Chart {
  
    private static Chart INSTANCE = new Chart();  

      
    public static Chart getInstance(){  
        return INSTANCE;  
    }  
      
    ChartPanel frame1;  
    public Chart(){  
        XYDataset xydataset = createDataset();  
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("chart", "日期", "价格",xydataset, true, true, true);  
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();  
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();  
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        dateaxis.setDateFormatOverride(format);  
        frame1=new ChartPanel(jfreechart,true);  
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状  
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
  
    }   
     
    private static XYDataset createDataset() 
    {  
        TimeSeries timeseries1 = new TimeSeries("cpu", Second.class);
        TimeSeries timeseries2 = new TimeSeries("net", Second.class);
        TimeSeries timeseries3 = new TimeSeries("mem", Second.class);
        List<Data> datas = new ArrayList<>();
        try 
        {
			datas = DAOFactory.getIDataDAOInstance().getData();
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
        for (Data data : datas)
        {
        	timeseries1.add(new Second(data.getDate()), data.getCpu());
        	timeseries2.add(new Second(data.getDate()), data.getNet());
        	timeseries3.add(new Second(data.getDate()), data.getMem());
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();  
        timeseriescollection.addSeries(timeseries1);  
        timeseriescollection.addSeries(timeseries2);  
        timeseriescollection.addSeries(timeseries3); 
        return timeseriescollection;  
    }  
	public ChartPanel getChartPanel()
	{  
	      return frame1;  
	}   
      
    public static float getCPU()
    {    
        float cpuUsage = 0;  
        Process pro1,pro2;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/stat";  
            //第一次采集CPU时间  
            //long startTime = System.currentTimeMillis();  
            pro1 = r.exec(command);  
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
            String line = null;  
            long idleCpuTime1 = 0, totalCpuTime1 = 0;   //分别为系统启动后空闲的CPU时间和总的CPU时间  
            while((line=in1.readLine()) != null){     
                if(line.startsWith("cpu")){  
                    line = line.trim();  
                    //System.out.println(line);
                    String[] temp = line.split("\\s+");   
                    idleCpuTime1 = Long.parseLong(temp[4]);  
                    for(String s : temp){  
                        if(!s.equals("cpu")){  
                            totalCpuTime1 += Long.parseLong(s);  
                        }  
                    }     
                    //System.out.println("IdleCpuTime: " + idleCpuTime1 + ", " + "TotalCpuTime" + totalCpuTime1);  
                    break;  
                }                         
            }     
            in1.close();  
            pro1.destroy();  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                StringWriter sw = new StringWriter();  
                e.printStackTrace(new PrintWriter(sw));  
                System.out.println("CpuUsage休眠时发生InterruptedException. " + e.getMessage());  
                System.out.println(sw.toString());  
            }  
            //第二次采集CPU时间  
            //long endTime = System.currentTimeMillis();  
            pro2 = r.exec(command);  
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));  
            long idleCpuTime2 = 0, totalCpuTime2 = 0;   //分别为系统启动后空闲的CPU时间和总的CPU时间  
            while((line=in2.readLine()) != null){     
                if(line.startsWith("cpu")){  
                    line = line.trim();  
                    //System.out.println(line);  
                    String[] temp = line.split("\\s+");   
                    idleCpuTime2 = Long.parseLong(temp[4]);  
                    for(String s : temp){  
                        if(!s.equals("cpu")){  
                            totalCpuTime2 += Long.parseLong(s);  
                        }  
                    }  
                    //System.out.println("IdleCpuTime: " + idleCpuTime2 + ", " + "TotalCpuTime" + totalCpuTime2);  
                    break;    
                }                                 
            }  
            if(idleCpuTime1 != 0 && totalCpuTime1 !=0 && idleCpuTime2 != 0 && totalCpuTime2 !=0){  
                cpuUsage = 1 - (float)(idleCpuTime2 - idleCpuTime1)/(float)(totalCpuTime2 - totalCpuTime1);  
                //System.out.println("本节点CPU使用率为: " + cpuUsage);  
            }                 
            in2.close();  
            pro2.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            //System.out.println("CpuUsage发生InstantiationException. " + e.getMessage());  
            //System.out.println(sw.toString());  
        }     
        return cpuUsage;  
    }  
  
    public static float getMem() 
    {  
    	//System.out.println("开始收集memory使用率");  
        float memUsage = 0.0f;  
        Process pro = null;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/meminfo";  
            pro = r.exec(command);  
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
            String line = null;  
            int count = 0;  
            long totalMem = 0, freeMem = 0;  
            while((line=in.readLine()) != null){      
            	//System.out.println(line);   
                String[] memInfo = line.split("\\s+");  
                if(memInfo[0].startsWith("MemTotal")){  
                    totalMem = Long.parseLong(memInfo[1]);  
                }  
                if(memInfo[0].startsWith("MemFree")){  
                    freeMem = Long.parseLong(memInfo[1]);  
                }  
                memUsage = 1- (float)freeMem/(float)totalMem;  
                //System.out.println("本节点内存使用率为: " + memUsage);   
                if(++count == 2){  
                    break;  
                }                 
            }  
            in.close();  
            pro.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            //System.out.println("MemUsage发生InstantiationException. " + e.getMessage());  
            //System.out.println(sw.toString());  
        }     
        return memUsage;  
    }  
    
    public void byteToImage(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
        System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
          System.out.println("Exception: " + ex);
          ex.printStackTrace();
        }
      }
    
    public static byte[] sendChart(JFrame frame, int weight, int height)throws Exception 
    {      
    	BufferedImage  bi = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D  g2d = bi.createGraphics();
    	frame.paint(g2d);
    	ImageIO.write(bi, "PNG", new File("frame.png"));
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	ImageIO.write(bi, "chart", out);
    	return out.toByteArray();
    }
    
    public static float getNet() {  
    	final float TotalBandwidth = 1000;
    	//System.out.println("开始收集网络带宽使用率");  
        float netUsage = 0.0f;  
        Process pro1,pro2;  
        Runtime r = Runtime.getRuntime();  
        try {  
            String command = "cat /proc/net/dev";  
            //第一次采集流量数据  
            long startTime = System.currentTimeMillis();  
            pro1 = r.exec(command);  
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));  
            String line = null;  
            long inSize1 = 0, outSize1 = 0;  
            while((line=in1.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth0")){  
                	//System.out.println(line);  
                    String[] temp = line.split("\\s+");   
                    inSize1 = Long.parseLong(temp[0].substring(5)); //Receive bytes,单位为Byte  
                    outSize1 = Long.parseLong(temp[8]);             //Transmit bytes,单位为Byte  
                    break;  
                }                 
            }     
            in1.close();  
            pro1.destroy();  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                StringWriter sw = new StringWriter();  
                e.printStackTrace(new PrintWriter(sw));  
                System.out.println("NetUsage休眠时发生InterruptedException. " + e.getMessage());  
                System.out.println(sw.toString());  
            }  
            //第二次采集流量数据  
            long endTime = System.currentTimeMillis();  
            pro2 = r.exec(command);  
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));  
            long inSize2 = 0 ,outSize2 = 0;  
            while((line=in2.readLine()) != null){     
                line = line.trim();  
                if(line.startsWith("eth0")){  
                	//System.out.println(line);  
                    String[] temp = line.split("\\s+");   
                    inSize2 = Long.parseLong(temp[0].substring(5));  
                    outSize2 = Long.parseLong(temp[8]);  
                    break;  
                }                 
            }  
            if(inSize1 != 0 && outSize1 !=0 && inSize2 != 0 && outSize2 !=0){  
                float interval = (float)(endTime - startTime)/1000;  
                //网口传输速度,单位为bps  
                float curRate = (float)(inSize2 - inSize1 + outSize2 - outSize1)*8/(1000000*interval);  
                netUsage = curRate/TotalBandwidth;  
                //System.out.println("本节点网口速度为: " + curRate + "Mbps");  
                //System.out.println("本节点网络带宽使用率为: " + netUsage);  
            }                 
            in2.close();  
            pro2.destroy();  
        } catch (IOException e) {  
            StringWriter sw = new StringWriter();  
            e.printStackTrace(new PrintWriter(sw));  
            System.out.println("NetUsage发生InstantiationException. " + e.getMessage());  
            System.out.println(sw.toString());  
        }     
        return netUsage;  
    }  
    
    /** 
     * @param args 
     * @throws InterruptedException  
     */  
    public static void main(String args[]){  
        JFrame frame=new JFrame("Java数据统计图");  
        frame.setLayout(new GridLayout(2,2,10,10));  
 
        frame.add(new Chart().getChartPanel());    //添加折线图  
        frame.setBounds(50, 50, 600, 600);  
        frame.setVisible(true);  
        try {
			sendChart(frame, 550, 550);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   
}
