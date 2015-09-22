/**
 * Fichier principal pour le probl�me
 * @author Tarek Ould Bachir, Wail Khemir
 * @date : 2015-09-06
 */

public class Main 
{
	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args) 
	{
		/**
		 * Exercice 1
		 */
		/*
		PixelMap pmc = new PixelMap("./ed.ppm");
		PixelMap pmg = pmc.toGrayImage();
		PixelMap pmb = pmc.toBWImage();
		
		PixelMap pmt = pmc.toTransparentImage();
		for(int i = 0; i < pmt.height; ++i)
			for(int j = 0; j < pmt.width; ++j)
				pmt.getPixel(i, j).setAlpha(127);
		
		String wName = "Edsger Dijkstra (original)";
		new DisplayImageWindow(wName, pmc, 50, 50);
		
		wName = "Edsger Dijkstra (gris)";
		new DisplayImageWindow(wName, pmg, 50+50, 50+50);
		
		wName = "Edsger Dijkstra (B&W)";
		new DisplayImageWindow(wName, pmb, 50+100, 50+100);
		
		wName = "Edsger Dijkstra (Transparent)";
		new DisplayImageWindow(wName, pmt, 200, 200);
		*/
		
		/**
		 * Exercice 2
		 */
		String wName = "Edsger Dijkstra";
		PixelMapPlus pmp = new PixelMapPlus("./ed.ppm");
		
		PixelMapPlus hpmp = new PixelMapPlus( pmp );
		hpmp.rotate(150, 150, (-Math.PI/3));
		/*
		hpmp.zoomIn(0, 0, 2);
		hpmp.resize(hpmp.width/2, hpmp.height/2);
		//new DisplayImageWindow(wName, hpmp);
		
		PixelMapPlus gpmp = new PixelMapPlus( pmp );
		gpmp.zoomIn(0, gpmp.height, 2);
		gpmp.resize(gpmp.width/2, gpmp.height/2);
		gpmp.convertToGrayImage();
		//new DisplayImageWindow(wName, gpmp);
		
		PixelMapPlus bwpmp = new PixelMapPlus( pmp );
		bwpmp.zoomIn(pmp.getWidth(), 0, 2);
		bwpmp.resize(bwpmp.width/2, bwpmp.height/2);
		bwpmp.convertToBWImage();
		//new DisplayImageWindow(wName, bwpmp);
		
		PixelMapPlus npmp = new PixelMapPlus( pmp );
		npmp.zoomIn(npmp.getWidth(), npmp.getHeight(), 2);
		npmp.resize(npmp.width/2, npmp.height/2);
		npmp.negate();
		//new DisplayImageWindow(wName, npmp);
		
		pmp.inset(hpmp, 0, 0);
		pmp.inset(gpmp, pmp.getHeight()/2, 0);
		pmp.inset(bwpmp, 0, pmp.getWidth()/2);
		pmp.inset(npmp, pmp.getHeight()/2, pmp.getWidth()/2);
		*/
		
		//String wName = "Edsger Dijkstra";
		new DisplayImageWindow(wName, hpmp);
		
	}
}
