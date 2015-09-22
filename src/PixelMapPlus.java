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
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( imageType == ImageType.BW ){
					imageData[row][col] = ((BWPixel)(this.getPixel(row, col))).Negative();
				} else if(imageType == ImageType.Gray ){
					imageData[row][col] = ((GrayPixel)(this.getPixel(row, col))).Negative();
				} else if(imageType == ImageType.Color){					
					imageData[row][col] = ((ColorPixel)(this.getPixel(row, col))).Negative();
				}
				else{
					imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).Negative();
				}
			}
		}
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( imageType == ImageType.BW ){
					//imageData[row][col] = ((BWPixel)(this.getPixel(row, col))).Negative();
				} else if(imageType == ImageType.Gray ){
					imageData[row][col] = ((GrayPixel)(this.getPixel(row, col))).toBWPixel();
				} else if(imageType == ImageType.Color){					
					imageData[row][col] = ((ColorPixel)(this.getPixel(row, col))).toBWPixel();
				}
				else{
					imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toBWPixel();
				}
			}
		}
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( imageType == ImageType.BW ){
					imageData[row][col] = ((BWPixel)(this.getPixel(row, col))).toGrayPixel();
				} else if(imageType == ImageType.Gray ){
					//imageData[row][col] = ((GrayPixel)(this.getPixel(row, col))).toGrayPixel();
				} else if(imageType == ImageType.Color){					
					imageData[row][col] = ((ColorPixel)(this.getPixel(row, col))).toGrayPixel();
				}
				else{
					imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toGrayPixel();
				}
			}
		}
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( imageType == ImageType.BW ){
					imageData[row][col] = ((BWPixel)(this.getPixel(row, col))).toColorPixel();
				} else if(imageType == ImageType.Gray ){
					imageData[row][col] = ((GrayPixel)(this.getPixel(row, col))).toColorPixel();
				} else if(imageType == ImageType.Color){					
					//imageData[row][col] = ((ColorPixel)(this.getPixel(row, col))).toColorPixel();
				}
				else{
					imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toColorPixel();
				}
			}
		}
		// compl�ter
		
	}
	
	public void convertToTransparentImage()
	{
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if( imageType == ImageType.BW ){
					imageData[row][col] = ((BWPixel)(this.getPixel(row, col))).toTransparentPixel();
				} else if(imageType == ImageType.Gray ){
					imageData[row][col] = ((GrayPixel)(this.getPixel(row, col))).toTransparentPixel();
				} else if(imageType == ImageType.Color){					
					imageData[row][col] = ((ColorPixel)(this.getPixel(row, col))).toTransparentPixel();
				}
				else{
					//imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toTransparentPixel();
				}
			}
		}
		// compl�ter
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
		PixelMapPlus nouveauPm = new PixelMapPlus(this.getType(), height, width);
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				double X = Math.cos(angleRadian)*col-Math.sin(angleRadian)*row+(-Math.cos(angleRadian)*x+Math.sin(angleRadian)*y+x);
				double Y = Math.sin(angleRadian)*col+Math.cos(angleRadian)*row+(-Math.sin(angleRadian)*x-Math.cos(angleRadian)*y+y);
				if(X > 0 && Y > 0 && Y < height && X < width){
					nouveauPm.imageData[(int)Y][(int)X] = this.getPixel(row, col);
				}
			}
		}
		this.imageData = nouveauPm.imageData;
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
		else{
			if(height>h && width>h){
				int propH = height/h;
				int propW = width/w;
				int cptPropH =0;
				int cptPropW =0;
				for(int row=0; row<h && cptPropH<height; row++)
				{
					for(int col=0; col<w && cptPropW<width; col++)
					{
						if( imageType == ImageType.BW ){
							imageData[row][col] = ((BWPixel)(this.getPixel(cptPropH, cptPropW)));
						} else if(imageType == ImageType.Gray ){
							imageData[row][col] = ((GrayPixel)(this.getPixel(cptPropH, cptPropW)));
						} else if(imageType == ImageType.Color){					
							imageData[row][col] = ((ColorPixel)(this.getPixel(cptPropH, cptPropW)));
						}
						else{
							//imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toTransparentPixel();
						}
						cptPropW+=propW;
					}
					cptPropH+=propH;
					cptPropW=0;
				}
			} else if(height<h && width<w){
				int propH = h/height;
				int propW = w/width;
				int cptPropH =0;
				int cptPropW =0;
				int cptH=0;
				int cptW=0;
				AbstractPixel[][] newImageData = new AbstractPixel[h][w];
					for(int row=0; row<h && cptPropH<height; row++)
					{
						if(cptH>=propH){
							cptH=0;
							cptPropH++;
						}
						for(int col=0; col<w && cptPropW<width; col++)
						{	
							if(cptW>=propW){
								cptW=0;
								cptPropW++;
							}
							if( imageType == ImageType.BW ){
								newImageData[row][col] = ((BWPixel)(this.getPixel(cptPropH, cptPropW)));
							} else if(imageType == ImageType.Gray ){
								newImageData[row][col] = ((GrayPixel)(this.getPixel(cptPropH, cptPropW)));
							} else if(imageType == ImageType.Color){					
								newImageData[row][col] = ((ColorPixel)(this.getPixel(cptPropH, cptPropW)));
							}
							else{
								//imageData[row][col] = ((TransparentPixel)(this.getPixel(row, col))).toTransparentPixel();
							}
							cptW++;
						}
						cptH++;
						cptPropW=0;
						cptW=0;
					}
					height=h;
					width=w;
					AllocateMemory(this.getType(), h, w);
					imageData=newImageData;
			}
		
			height=h;
			width=w;
			
		}
		// compl�ter
		
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		int rowPm = 0;
		int colPm = 0;
		for(int row=row0; row<height && rowPm<pm.height; row++)
		{
			for(int col=col0; col<width && colPm<pm.width ; col++)
			{
				imageData[row][col] = pm.getPixel(rowPm, colPm);
				colPm++;
			}
			rowPm++;
			colPm=0;
		}
		// compl�ter
		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{	
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		else{
			PixelMapPlus nouveauPm = new PixelMapPlus(this.getType(), h, w);
			nouveauPm.inset(this, 0, 0);
			height = nouveauPm.height;
			width = nouveauPm.width;
			imageData = nouveauPm.imageData;
		}
		// compl�ter		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		for(int row=0; row<rowOffset; row++)
		{
			for(int col=0; col<colOffset; col++)
			{
				if(this.getType() == ImageType.BW ){
					imageData[row][col]=new BWPixel(true);
				} else if(this.getType() == ImageType.Gray ) {
					imageData[row][col] =new GrayPixel(255);
				} else if(this.getType()== ImageType.Color ){					
					int[] tableau = {255, 255, 255};
					imageData[row][col] = new ColorPixel(tableau);
				} else{
					int[] rgba = {255, 255, 255, 255};
					imageData[row][col] = new TransparentPixel(rgba);
				}
			}
		}
		
		for(int row=rowOffset; row<height; row++)
		{
			for(int col=colOffset; col<width; col++)
			{
				imageData[row][col]=getPixel(row, col);
			}
		}
		// compl�ter		
		
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
		int widthInit = width;
		int heightInit = height;
		double widthCadre = width/zoomFactor;
		double heightCadre = height/zoomFactor;
		int coinSupGaucheX = (int)(x-(widthCadre/2));
		int coinSupGaucheY = (int)(y-(heightCadre/2));
		
		int coinSupDroitX = coinSupGaucheX+ (int)widthCadre;
		int coinSupDroitY = coinSupGaucheY;
	
		int coinInfGaucheX = coinSupGaucheX ;
		int coinInfGaucheY = coinSupGaucheY + (int)heightCadre;
		
		int coinInfDroitX = coinSupDroitX;
		int coinInfDroitY = coinInfGaucheY;
		
		if(coinSupGaucheX<0){
			/*
			 * Version logique :
			 * widthCadre+=coinSupGaucheX;
			 * coinSupGaucheX = 0;
			 */
			coinSupGaucheX = 0;
		}
		if(coinSupGaucheY<0){
			/*
			 * Version logique : 
			 * heightCadre+=coinSupGaucheY;
			 * coinSupGaucheY = 0;
			 */
			coinSupGaucheY = 0;
		}
		
		if(coinSupDroitX>width){
			int ecart = coinSupDroitX-width;
			coinSupDroitX = width;	
			coinSupGaucheX -= ecart;			
			/*
			 * Version logique
			 * coinSupDroitX = width;
			 * widthCadre=width-coinSupGaucheX;
			 */
		}
		if(coinInfGaucheY>height){
			/*
			 * Version logique
			 * coinInfGaucheY = height;
			 * heightCadre=height-coinSupGaucheY;
			 */
			int ecart = coinInfGaucheY-height;
			coinInfGaucheY = height;
			coinSupGaucheY -= ecart;
		}
		
		PixelMapPlus nouveauPm = new PixelMapPlus(this.getType(),(int)heightCadre, (int)widthCadre);
			
		int rowCadre= coinSupGaucheY;
		int colCadre = coinSupGaucheX-1;
		for(int row=0; row<nouveauPm.height ; row++)
		{
			for(int col=0; col<nouveauPm.width ; col++)
			{
				colCadre++;
				nouveauPm.imageData[row][col] = this.getPixel(rowCadre, colCadre);
			}
			rowCadre++;
			colCadre = coinSupGaucheX-1 ;
		}
		height = nouveauPm.height;
		width = nouveauPm.width;
		imageData = nouveauPm.imageData;
		
		resize(widthInit, heightInit);
		// compl�ter
		
	}
}
