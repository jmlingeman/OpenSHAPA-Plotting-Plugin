
/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ------------------------
 * CombinedXYPlotDemo4.java
 * ------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited).
 * Contributor(s):   -;
 *
 * $Id $
 *
 * Changes
 * -------
 * 29-Jul-2003 : Version 1 (DG);
 * 27-Apr-2004 : Modified for changes to XYPlot (DG);
 *
 */

package ascensionxyplot;

import java.awt.Font;
import java.io.File;
import java.util.Vector;
import java.util.Random;
import java.util.Scanner;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing how to create a vertical combined chart.
 *
 */
public class AscensionXYPlot extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public AscensionXYPlot(final String title, final File dataFile) {

        super(title);
        final JFreeChart chart = createCombinedChart(dataFile);
        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    /**
     * Creates a combined chart.
     *
     * @return The combined chart.
     */
    private JFreeChart createCombinedChart(File dataFile) {

        // create subplot 1...
        final XYDataset data1 = createDataset1(dataFile);
        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
        final NumberAxis rangeAxis1 = new NumberAxis("Range 1");
        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // add secondary axis
//        subplot1.setDataset(1, createDataset2());
//        final NumberAxis axis2 = new NumberAxis("Range Axis 2");
//        axis2.setAutoRangeIncludesZero(false);
//        subplot1.setRangeAxis(1, axis2);
//        subplot1.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
//        subplot1.setRenderer(1, new StandardXYItemRenderer());       
//        subplot1.mapDatasetToRangeAxis(1, 1);

        final XYTextAnnotation annotation1 = new XYTextAnnotation("x value", 20.0, 120000.0);
        final XYTextAnnotation annotation2 = new XYTextAnnotation("y value", 20.0, 110000.0);
        final XYTextAnnotation annotation3 = new XYTextAnnotation("z value", 20.0, 100000.0);
        final XYTextAnnotation annotation4 = new XYTextAnnotation("timepoint", 20.0, 10000.0);
        annotation1.setFont(new Font("SansSerif", Font.PLAIN, 9));
        annotation2.setFont(new Font("SansSerif", Font.PLAIN, 9));
        annotation3.setFont(new Font("SansSerif", Font.PLAIN, 9));
        annotation4.setFont(new Font("SansSerif", Font.PLAIN, 9));
//        annotation.setRotationAngle(Math.PI / 4.0);
        subplot1.addAnnotation(annotation1);
        subplot1.addAnnotation(annotation2);
        subplot1.addAnnotation(annotation3);
        subplot1.addAnnotation(annotation4);
        
        // create subplot 2...
        final XYDataset data2 = createDataset2();
        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
        final NumberAxis rangeAxis2 = new NumberAxis("Range 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

        // parent plot...
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap(10.0);
        
        // add the subplots...
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        return new JFreeChart("Is this the title?",
                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);

    }

    /**
     * Creates a sample dataset.
     *
     * @return Series 1.
     */
    private XYDataset createDataset1(File dataFile) {
        
        Body body = DataReader.ReadAscensionControlLog(dataFile);

        Vector<Point> p = body.markers.get(String.valueOf(0)).points;

        // create dataset 1...
        final XYSeries series1a = new XYSeries("x");
//        series1.add(10.0, 12353.3);
//        series1.add(20.0, 13734.4);


        final XYSeries series1b = new XYSeries("y");
//        series1b.add(10.0, 15000.3);
//        series1b.add(20.0, 11000.4);

        final XYSeries series1c = new XYSeries("z");
//        series1b.add(10.0, 15000.3);
//        series1b.add(20.0, 11000.4);

		Long lowerBound = 0L;
		Long upperBound = 120L;
        
        Long previousTime = lowerBound;        
        int i = 0;
        
        while (previousTime < upperBound) {
            Point currentPoint = p.elementAt(i);
            Long currentTime = Long.valueOf(body.timepoints.elementAt(i).toString());
            
            if ( currentTime == lowerBound || currentTime - previousTime >= 10 ) {
            	// plot a point if it is the first record or more than 0.01s passed since last plot
            	// note: the actual time was multiplied by 1000 in DataReader, thus requiring time difference of 10
            	series1a.add(currentPoint.time, currentPoint.x);
                series1b.add(currentPoint.time, currentPoint.y);
//                series1c.add(currentPoint.z, currentTime);
                series1c.add(currentPoint.time, currentPoint.z); //HOW IS THIS NOT WORKING??? WHYYYYYY....
                
                previousTime = currentTime;
//                System.out.println(previousTime);
//                System.out.println(currentTime);
//                System.out.println(currentPoint.time);
            }
            
            i++;
        }
        
      
        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1a);
        collection.addSeries(series1b);
        collection.addSeries(series1c);
        return collection;

    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private XYDataset createDataset2() {

        // create dataset 2...
        final XYSeries series2 = new XYSeries("PitchRollYaw for example");

        series2.add(0.0, 19642.3);
        series2.add(1.0, 18253.5);
        series2.add(2.0, 15352.3);
        series2.add(3.0, 13532.0);
        series2.add(4.0, 12635.3);
        series2.add(5.0, 13998.2);
        series2.add(6.0, 11943.2);
        series2.add(7.0, 16943.9);
        series2.add(8.0, 17843.2);
        series2.add(9.0, 16495.3);
        series2.add(10.0, 17943.6);
        series2.add(11.0, 18500.7);
        series2.add(12.0, 19595.9);

        return new XYSeriesCollection(series2);

    }


}