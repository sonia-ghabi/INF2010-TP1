import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// On parcours tous les pixels de l'image et convertit chaque pixel en négatif
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = (this.getPixel(row, col)).Negative();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// On parcours tous les pixels de l'image et convertit chaque pixel en noir et blanc
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = (this.getPixel(row, col)).toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// On parcours tous les pixels de l'image et convertit chaque pixel en ton de gris
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = (this.getPixel(row, col)).toGrayPixel();
			}
		}		
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// On parcours tous les pixels de l'image et convertit chaque pixel en couleur
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = (this.getPixel(row, col)).toColorPixel();
			}
		}
	}
	
	public void convertToTransparentImage()
	{
		// On parcours tous les pixels de l'image et convertit chaque pixel en transparent
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = (this.getPixel(row, col)).toTransparentPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		PixelMapPlus newPm = new PixelMapPlus(this.getType(), height, width);
		
		// On parcours tous les pixels de la nouvelle image et applique la formule pour trouver quel 
		// est le pixel correspondant dans l'image d'origine
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				double X = Math.cos(angleRadian)*col+Math.sin(angleRadian)*row+(-Math.cos(angleRadian)*x-Math.sin(angleRadian)*y+x);
				double Y = -Math.sin(angleRadian)*col+Math.cos(angleRadian)*row+(Math.sin(angleRadian)*x-Math.cos(angleRadian)*y+y);
				if((int)X >= 0 && (int)Y >= 0 && (int)Y < height && (int)X < width){
					newPm.imageData[row][col] = this.getPixel((int)Y, (int)X);
				}
			}
		}
		clearData();
		// On remplace l'ancienne image par la nouvelle
		this.imageData = newPm.imageData;
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		// On crée une nouvelle image avec la nouvelle hauteur et largeur
		PixelMapPlus newPm = new PixelMapPlus(this.getType(), h, w);
		
		// Indices pour l'image d'origine
		int rowOrigin = 0;
		int colOrigin = 0;
		
		// Si on a un rapetissement de l'image
		if(height>h && width>h){
			
			// On calcule la proportion du rapetissement
			int propH = height/h;
			int propW = width/w;
			
			for(int row=0; row<h && rowOrigin<height; row++)
			{
				for(int col=0; col<w && colOrigin<width; col++)
				{
					newPm.imageData[row][col] = this.getPixel(rowOrigin, colOrigin);
					// On saute jusqu'au prochain pixel en colonne à copier (qui n'est pas le suivant, cela dépend
					// de la proportion du rapetissement)
					colOrigin+=propW;
				}
				// On saute jusqu'au prochain pixel en ligne à copier (qui n'est pas le suivant, cela dépend
				// de la proportion du rapetissement)
				// On réinitialise la position de la colonne
				rowOrigin+=propH;
				colOrigin=0;
			}
		// Si on a un agrandissement
		} else if(height<h && width<w){
			
			// On calcule la proportion de l'agrandissement
			int propH = h/height;
			int propW = w/width;;
			
			// Ces compteurs permettent de suivre combien de fois le même pixel a été copié
			int cptH = 0;
			int cptW = 0;
			
			for(int row = 0; row < h && rowOrigin < height; row++)
			{
				// Si on a copié assez de fois le pixel en hauteur, on réinitialise le compteur et on peut incrémenter la ligne
				if(cptH >= propH){
					cptH = 0;
					rowOrigin++;
				}
				for(int col = 0; col < w && colOrigin < width; col++)
				{	
					// Si on a copié assez de fois le pixel en largeur, on réinitialise le compteur et on peut incrémenter la colonne
					if(cptW >= propW){
						cptW = 0;
						colOrigin++;
					}
					newPm.imageData[row][col] = this.getPixel(rowOrigin, colOrigin);
					cptW++;
				}
				cptH++;
				colOrigin = 0;
				cptW = 0;
			}
		}
		// On remplace les attributs de l'image d'origine par les nouveaux
		height = h;
		width = w;
		clearData();
		imageData = newPm.imageData;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		int rowPm = 0;
		int colPm = 0;
		for(int row = row0; row < height && rowPm < pm.height; row++)
		{
			for(int col = col0; col < width && colPm < pm.width ; col++)
			{
				imageData[row][col] = pm.getPixel(rowPm, colPm);
				colPm++;
			}
			rowPm++;
			colPm=0;
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{	
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		else{
			// On crée une image blanche de hauteur et largeur h et w
			PixelMapPlus newPm = new PixelMapPlus(this.getType(), h, w);
			// Dans laquelle on insère notre image d'origine à la position (0, 0)
			newPm.inset(this, 0, 0);
			// On remplace les attributs de l'image d'origine par les nouveaux
			height = newPm.height;
			width = newPm.width;
			clearData();
			imageData = newPm.imageData;
		}		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// On crée une image blanche de hauteur et largeur h et w
		PixelMapPlus newPm = new PixelMapPlus(this.getType(), height, width);
		// On copie les pixels à partir de l'offset
		for(int row=rowOffset; row<height; row++)
		{
			for(int col=colOffset; col<width; col++)
			{
				newPm.imageData[row][col]=getPixel(row, col);
			}
		}
		// On remplace les attributs de l'image d'origine par les nouveaux
		height = newPm.height;
		width = newPm.width;
		clearData();
		imageData = newPm.imageData;		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// On sauvegarde la hauteur et largeur initiale
		int widthInit = width;
		int heightInit = height;
		
		// On calcule la largeur et la hauteur de la zone à zoomer
		double frameWidth = width/zoomFactor;
		double frameHeight = height/zoomFactor;
		
		// On définit la position de certains points stratégiques
		int upLeftCornerX = (int)(x-(frameWidth/2));
		int upLeftCornerY = (int)(y-(frameHeight/2));
		int upRightCornerX = upLeftCornerX+ (int)frameWidth;
		int downLeftCornerY = upLeftCornerY + (int)frameHeight;
		
		// Si le coin supérieur gauche est hors de l'image sur la largeur, on déplace ce point 
		// (et par conséquent le cadre) à l'origine
		if(upLeftCornerX < 0){
			/*
			 * Version logique :
			 * widthCadre+=upLeftCornerX;
			 * upLeftCornerX = 0;
			 */
			upLeftCornerX = 0;
		}
		// Si le coin supérieur gauche est hors de l'image sur la hauteur, on déplace ce point 
		// (et par conséquent le cadre) à l'origine
		if(upLeftCornerY < 0){
			/*
			 * Version logique : 
			 * heightCadre+=upLeftCornerY;
			 * upLeftCornerY = 0;
			 */
			upLeftCornerY = 0;
		}
		
		// Si le coin supérieur droit est hors de l'image sur la largeur, on déplace ce point 
		// (et par conséquent le cadre) on ramène ce point au bord et déplace le cadre
		if(upRightCornerX > width){
			int gap = upRightCornerX-width;
			upRightCornerX = width;	
			upLeftCornerX -= gap;			
			/*
			 * Version logique
			 * upRightCornerX = width;
			 * widthCadre=width-upLeftCornerX;
			 */
		}
		// Si le coin inférieur gauche est hors de l'image sur la hauteur, on déplace ce point 
		// (et par conséquent le cadre) on ramène ce point au bord et déplace le cadre
		if(downLeftCornerY>height){
			/*
			 * Version logique
			 * coinInfGaucheY = height;
			 * heightCadre=height-coinSupGaucheY;
			 */
			int gap = downLeftCornerY-height;
			downLeftCornerY = height;
			upLeftCornerY -= gap;
		}
		
		// On crée une nouvelle image qui est le cadre du zoom
		PixelMapPlus newPm = new PixelMapPlus(this.getType(),(int)frameHeight, (int)frameWidth);
			
		int rowFrame= upLeftCornerY;
		int colFrame = upLeftCornerX-1;
		// On copie seulement les pixels à zoomer
		for(int row=0; row<newPm.height ; row++)
		{
			for(int col=0; col<newPm.width ; col++)
			{
				colFrame++;
				newPm.imageData[row][col] = this.getPixel(rowFrame, colFrame);
			}
			rowFrame++;
			colFrame = upLeftCornerX-1 ;
		}
		
		// On remplace les attributs de l'image d'origine par les nouveau
		height = newPm.height;
		width = newPm.width;
		clearData();
		imageData = newPm.imageData;
		// Et on resize pour avoir la bonne taille
		resize(widthInit, heightInit);
	}
}
