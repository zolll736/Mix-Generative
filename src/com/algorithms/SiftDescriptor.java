package com.algorithms;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;	/*For debugging*/
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SiftDescriptor{
	private byte[] mapping;
	public byte[][] neighbourhood;
	public  byte[][] mapp1;
	private static byte[][] doublepixel ;
	private static byte[][] doublepixel1 ;
	private int radius;
	private int samples;
	public byte[] cutPoints;
	
	public SiftDescriptor(int samples, int radius){
		this.samples	= samples;
		this.radius		= radius;
		cutPoints = new byte[samples+2];
		for (int i = 0; i<samples+2;++i){
			cutPoints[i] = (byte) i;
		}
		mapping = getMapping(samples);
		//System.out.println("Mapping length "+mapping.length+" samples "+samples);
		neighbourhood = getCircularNeighbourhood(radius,samples);
		
	}
	
	public byte[][] getKeypoints(byte[][] data){
		int width = data.length;
		int height = data[0].length;
		byte[][] lbpSlice = new byte[width][height];
		
		int[] coordinates = new int[2];
		for (int i = 0+radius;i<width-radius;++i){
			for (int j = 0+radius;j<height-radius;++j){
				coordinates[0] = i;
				coordinates[1] = j;
				//System.out.println("source x "+coordinates[0]+" y "+coordinates[1]);
				lbpSlice[i][j] = SiftDescriptorBlock(data,coordinates);
			}
		}
		
		return lbpSlice;
	}
	
	private byte SiftDescriptorBlock(byte[][] data,int[] coordinates){
		int lbpValue = 0;
		double x = (double) coordinates[0];
		double y = (double) coordinates[1];
		for (int i = 0; i<neighbourhood.length;++i){
			lbpValue = data[(int) x][(int) y] > getBicubicInterpolatedPixel(x+neighbourhood[i][0],y+neighbourhood[i][1],data)+3.0 ? lbpValue | (1 << i) : lbpValue & ~(1 << i);
		}
		return mapping[lbpValue];
	}

	
	private static byte[] getMapping(int samples){
		int bitMaskLength = (int) (Math.pow(2.0,(double) samples));
		byte[] table = new byte[bitMaskLength];
		int j;
		int sampleBitMask = 0;
		for (int i = 0;i<samples;++i){
			sampleBitMask |= 1<<i;
		}
		
		int numt;
		for (int i = 0;i<bitMaskLength;++i){
			j = ((i<<1) & sampleBitMask); 
			j = (i>>(samples-1)) >0 ? j | 1: j & ~1;	
			numt = 0;
			for (int k = 0;k<samples;++k){
				numt+= (((i^j)>>k) & 1);
			}
			
			if (numt <= 2){
				for (int k = 0;k<samples;++k){
					table[i]+= (i>>k) & 1;
				}
			}else{
				table[i] = (byte) (samples+1);
			}
		}
		return table;
	}
	
	
	private static byte[][] getCircularNeighbourhood(int radius,int samples){
		byte[][] samplingCoordinates = new byte[samples][2];
		final byte angleIncrement = (byte) (Math.PI*2.0/(byte) samples);
		for (int n = 0;n <samples; ++n){
			samplingCoordinates[n][0] = (byte) (((byte) radius)*Math.cos(((byte)n)*angleIncrement));
			samplingCoordinates[n][1] = (byte) (((byte) radius)*Math.sin(((byte)n)*angleIncrement));
		}
		return samplingCoordinates;
	}
	
	
	public static double getBicubicInterpolatedPixel(double x0, double y0, byte[][] data) {
		int u0 = (int) Math.floor(x0);	
		int v0 = (int) Math.floor(y0);
		int width = data.length;
		int height = data[0].length;
		if (u0<1 || u0>width-3 || v0< 1 || v0>height-3){
			if ((u0 == 0 || u0 < width-1) && (v0 == 0 || v0 < height-1)){ 
				double x = (x0-(double)u0);
				double y = (y0-(double)v0);
				return data[u0][v0]*(1-x)*(1-y) 	
						+data[u0+1][v0]*(1-y)*x	
						+data[u0][v0+1]*(1-x)*y	
						+data[u0+1][v0+1]*x*y;	
			}
			return 0; 
		}
		double q = 0;
		for (int j = 0; j < 4; ++j) {
			int v = v0 - 1 + j;
			double p = 0;
			for (int i = 0; i < 4; ++i) {
				int u = u0 - 1 + i;
				p = p + data[u][v] * cubic(x0 - u);
			}
			q = q + p * cubic(y0 - v);
		}
		return q;
	}
	
	
	public static final double cubic(double x) {
		final double a = 0.5; 
		if (x < 0.0) x = -x;
		double z = 0.0;
		if (x < 1.0) 
			z = x*x*(x*(-a+2.0) + (a-3.0)) + 1.0;
		else if (x < 2.0) 
			z = -a*x*x*x + 5.0*a*x*x - 8.0*a*x + 4.0*a;
		return z;
	}
	
	
	public double[] histc(double[] values,double[] cutPoints){
		double[] histogram = new double[cutPoints.length];
		for (int i = 0;i<values.length;++i){
			int j = 0;
			
			
			while (j < cutPoints.length-2 && values[i] >= cutPoints[j+1]){
				++j;
			}
			if (values[i] == cutPoints[cutPoints.length-1]){
				j = j+1;
			}
			
			histogram[j] += 1;
		}
		
		return histogram;
	}
	
	public double[] histc(double[] values){
		double[] histogram = new double[cutPoints.length];
		for (int i = 0;i<values.length;++i){
			int j = 0;
			
			
			while (j < cutPoints.length-2 && values[i] >= cutPoints[j+1]){
				++j;
			}
			if (values[i] == cutPoints[cutPoints.length-1]){
				j = j+1;
			}
			
			histogram[j] += 1;
		}
		histogram = arrDiv(histogram,sum(histogram));
		return histogram;
	}
	
	
	public double[] reshape(double[][] dataIn,int xb,int xe,int yb,int ye){
		double[] array = new double[(xe-xb+1)*(ye-yb+1)];
		int ind = 0;
		for (int y = yb;y<=ye;++y){
			for (int x = xb;x<=xe;++x){
				array[ind] = dataIn[x][y];
				++ind;
			}
		}
		return array;
	}
	

	public static double[] reshape(double[][][] dataIn,int xb,int xe,int yb,int ye, int db, int de){
		double[] array = new double[(xe-xb+1)*(ye-yb+1)*(de-db+1)];
		int ind = 0;
		for (int d = db;d<=de;++d){
			for (int y = yb;y<=ye;++y){
				for (int x = xb;x<=xe;++x){
					array[ind] = dataIn[x][y][d];
					++ind;
				}
			}
		}
		return array;
	}
	
	
	
	private double sum(double[] arrayIn){
		double temp = 0;
		for (int i = 0;i< arrayIn.length;++i){
			temp+=arrayIn[i];
		}
		return temp;
	}
	private double[] arrDiv(double[] arrayIn,double divisor){
		for (int i = 0;i< arrayIn.length;++i){
			arrayIn[i]/=divisor;
		}
		return arrayIn;
	}
	
	public double checkClose(double[] sampleHist,double[] modelHist){
        double closeness = 0;
        for (int h = 0;h<sampleHist.length;++h){
            closeness += min(sampleHist[h],modelHist[h]);
        }
		return closeness;
    }
	private double min(double a, double b){
		return (a < b) ? a : b;
	}
	

	
	public static byte[][] convertToArrayLocation(String image) throws IOException{
		
		  BufferedImage img= ImageIO.read(new File(image));
		    Raster raster=img.getData();
		    int w=raster.getWidth(),h=raster.getHeight();
		    
		    int result[][]=new int[w][h];
		    for (int x=0;x<w;x++)
		    {
		        for(int y=0;y<h;y++)
		        {
		        	result[x][y]=raster.getSample(x,y,0);
		        }
		    }

		    doublepixel = new byte[result.length][result[0].length];

		      for(int i = 0; i < result.length; i++)
		      {
		    	  for(int j = 0; j < result[0].length; j++)
		    	  {
		         
		        	  doublepixel[i][j] = (byte) result[i][j];
		    	  }
		      }
		    return doublepixel;

		}
	public static ArrayList printMatrix(byte[][] doublepixel12)
	{
		DecimalFormat f = new DecimalFormat("0.#");
		String result="";
		ArrayList<String> list=new ArrayList<String>();
		for (int x = 0; x< doublepixel12.length;++x)
			
		{
			for (int y = 0; y<doublepixel12[x].length;++y)
			{
				
				
				
				result=f.format(doublepixel12[x][y]);
				
				System.out.print(result+"\t");
				
				
				list.add(result+"\t");
			
				
			}
			list.add("\n");
			System.out.println("\n");
		}
		
		return list;
	}
	
}