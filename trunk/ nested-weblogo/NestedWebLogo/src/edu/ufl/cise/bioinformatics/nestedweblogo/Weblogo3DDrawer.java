package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
//import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.NestedWebLogoCreator;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoColumn;
import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

public class Weblogo3DDrawer extends JPanel  {
	/**
	 * 
	 */
	private String webLogoFilePath = "C:\\Users\\sagar\\MyStudy\\BioInformatics\\nestedsample.txt";
	
	Map<String,NestedWebLogoDataStructure> nestedWeblogoMap = new HashMap<String, NestedWebLogoDataStructure>();
	
	private static final long serialVersionUID = 1L;
	
	private static final int X_INTERVAL = 15;
	
	String words = "Valour fate kinship darkness";
	String java = "Java TM";

	ArrayList<WeblogoColumn> columnList = new ArrayList<WeblogoColumn>();
	
	private int preferredSizeWidth = 800;
	private int preferredSizeHeight = 800;
	
	private enum LineType {
		HORIZONTAL, VERTICAL
	}
	
	
	
	/**
	 * Inits the.
	 */
	public void init(){
		test();
		
	    /*Utilities utilities = new Utilities();

		WeblogoDataStructure dataStructure =  utilities.getWeblogoDS(webLogoFilePath);
	    columnList = dataStructure.getColumnList();
		
		NestedWebLogoCreator creator = new NestedWebLogoCreator(webLogoFilePath);
		NestedWebLogoDataStructure nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "A.*");
		addEntryToNestedWebLogoMap(nestedLogo);

		nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "T.*");
		addEntryToNestedWebLogoMap(nestedLogo);
		
		nestedLogo =  creator.getNestedLogo(2, 4, 5, 7, "TA.*");
		addEntryToNestedWebLogoMap(nestedLogo);*/
		
	}	
	
	public void test(){
		this.setPreferredSize(new Dimension(800, 800));
		String seq1 = "AGTC";
        String seq2 = "ATGC";
        String seq3 = "TAGT";
        
        String[] seqarray = new String[3];
        seqarray[0] = seq1;
        seqarray[1] = seq2;
        seqarray[2] = seq3;
		
		WeblogoDataStructure weblogoData = WeblogoDataStructure.getInstance();
		weblogoData.calculateHeight(seqarray);
		
		WeblogoColumn webLogoColumn = new WeblogoColumn();

		LinkedHashMap<String, Double> charactersMap = new LinkedHashMap<String, Double>();
		
		charactersMap.put("A", 1.0);
		charactersMap.put("C", 2.0);
		charactersMap.put("G", 3.0);
		charactersMap.put("T", 4.0);

		
		webLogoColumn.setCharactersMap(charactersMap);
		
//		columnList = weblogoData.getColumnList();

		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		columnList.add(webLogoColumn);
		
		
		WeblogoDataStructure webLogo = WeblogoDataStructure.getInstance();
		charactersMap = new LinkedHashMap<String, Double>();
		
		charactersMap.put("C", 2.0);
		charactersMap.put("G", 1.0);
		
		webLogoColumn = new WeblogoColumn();
		webLogoColumn.setCharactersMap(charactersMap);
		
		webLogo.getColumnList().add(webLogoColumn);
		webLogo.getColumnList().add(webLogoColumn);
		webLogo.getColumnList().add(webLogoColumn);
		webLogo.getColumnList().add(webLogoColumn);
	}
	
	public void buildWebLogo(Map<String,NestedWebLogoDataStructure> nestedWeblogoMap){
		
		NestedWebLogoCreator creator = new NestedWebLogoCreator(webLogoFilePath);
		
		Set<String> keySet = nestedWeblogoMap.keySet();
		
		if(keySet.isEmpty() == false){
			clearNestedWebLogoMap();
		}
		
		for (String key : keySet) {
			NestedWebLogoDataStructure nestedWebLogoInput = nestedWeblogoMap.get(key);
			NestedWebLogoDataStructure nestedLogo =  creator.getNestedLogo(nestedWebLogoInput.getSourceWebLogoStartPosition(), nestedWebLogoInput.getSourceWebLogoEndPosition(), nestedWebLogoInput.getTargetWebLogoStartPosition(), nestedWebLogoInput.getTargetWebLogoEndPosition(), nestedWebLogoInput.getWildCardPattern());
			addEntryToNestedWebLogoMap(nestedLogo);
		}
		
	}
	
	public void clearNestedWebLogoMap(){
		
		nestedWeblogoMap.clear();
	}
	
	public void addEntryToNestedWebLogoMap(NestedWebLogoDataStructure nestedWebLogoDataStructure){
		
		String key = nestedWebLogoDataStructure.getWildCardPattern()+":"+nestedWebLogoDataStructure.getSourceWebLogoStartPosition()+":"
		+nestedWebLogoDataStructure.getSourceWebLogoEndPosition()+":"+nestedWebLogoDataStructure.getTargetWebLogoStartPosition()+":"
		+nestedWebLogoDataStructure.getTargetWebLogoEndPosition();
		
		nestedWeblogoMap.put(key, nestedWebLogoDataStructure);
	}
	
	@Override
	public void paint(Graphics g) {

		setBackground(Color.WHITE);
		
		super.paintComponent(g);
		

//		System.out.println("Painting: "+preferredSizeWidth + "   "+preferredSizeHeight);
		
		Graphics2D graphics2DObject = (Graphics2D) g;
		
		int bottomLeftXPosition = 60;
		int topLeftXPosition = 60;
		int topLeftYPosition = 42;
		int bottomLeftYPosition = getHeight()-42;
		int bottomRightYPosition = getHeight()-42;
		int bottomRightXPosition = getWidth()-100;
		int maximumAvailableWidth = getWidth()-100;
		float maximumAvailableHeight = getHeight()-142;
		
//		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumAvailableWidth, maximumAvailableHeight);
		
//		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , maximumAvailableHeight/4+40 , maximumAvailableWidth/4 , maximumAvailableHeight/4);
		
		drawMainWebLogo(graphics2DObject, columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumAvailableWidth, maximumAvailableHeight);
		
//		drawNestedWebLogo(graphics2DObject, columnList, bottomLeftXPosition, bottomLeftYPosition, maximumAvailableWidth, maximumAvailableHeight);
		
		drawYAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition, topLeftXPosition, topLeftYPosition );
		
		drawXAxis(graphics2DObject, bottomLeftXPosition, bottomLeftYPosition , bottomRightXPosition , bottomRightYPosition);
								
	}
	
	
	public int[][] getValidIntervalForMainWebLogo(){

		//  Start position of main sequence
		int startPosition = 1;
		
		//  End position of main sequence
		int endPosition = columnList.size();

		Set<String> keys =  nestedWeblogoMap.keySet();
		
		int intervalSet[][] = new int[keys.size()*2][2];
		
		int count = 0;
		
		for (String key : keys) {
//			System.out.println("Key: " +key);
			NestedWebLogoDataStructure nestedWebLogo = nestedWeblogoMap.get(key);

			intervalSet[count][0] = nestedWebLogo.getSourceWebLogoStartPosition();
			intervalSet[count][1] = nestedWebLogo.getSourceWebLogoEndPosition();
			
			count++;
		
			intervalSet[count][0] = nestedWebLogo.getTargetWebLogoStartPosition();
			intervalSet[count][1] = nestedWebLogo.getTargetWebLogoEndPosition();
			
			count++;
		}
		
		for(int i=0 ; i<intervalSet.length ; i++){
			for(int j=i+1 ; j<intervalSet.length ; j++){
				if(intervalSet[i][0] > intervalSet[j][0]){
					int temp = intervalSet[i][0];
					intervalSet[i][0] = intervalSet[j][0];
					intervalSet[j][0] = temp;
				}
			}
		}
		
		int[][] mainWebLogoInterval = new int[intervalSet.length+1][2];
		
		mainWebLogoInterval[0][0] = startPosition;
		 
		
		for(int i=0 ; i<intervalSet.length ; i++){
			mainWebLogoInterval[i][1] = intervalSet[i][0]-1;
			mainWebLogoInterval[i+1][0] = intervalSet[i][1]+1;
		}
		
		mainWebLogoInterval[intervalSet.length][1] = endPosition;
		
		return mainWebLogoInterval;
	}
	
	public void drawMainWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight){
		// Get the interval
		
//		int[][] validInterval = getValidIntervalForMainWebLogo();
		
		drawWebLogo(graphics2DObject, columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight,0,columnList.size());
		
	}
	
	public void drawNestedWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight){
		
		HashMap<String,Integer> columnGroupMap = new HashMap<String, Integer>();
		
		HashMap<String,Integer> rankOfWebLogoInColumnMap = new HashMap<String, Integer>();

		Set<String> keys = nestedWeblogoMap.keySet();
		
		for (String key : keys) {
			NestedWebLogoDataStructure nestedWebLogo =  nestedWeblogoMap.get(key);
			
			String columnKey = nestedWebLogo.getSourceWebLogoStartPosition()+":"+nestedWebLogo.getSourceWebLogoEndPosition();
			
			int numberOfWeblogoInColumn = 1;
			
			if(columnGroupMap.containsKey(columnKey)){
				numberOfWeblogoInColumn = columnGroupMap.get(columnKey) + 1;
			}

			columnGroupMap.put(columnKey, numberOfWeblogoInColumn);
			rankOfWebLogoInColumnMap.put(key, numberOfWeblogoInColumn);
			
		}
		
		
		for (String key : keys) {
			NestedWebLogoDataStructure nestedWebLogo =  nestedWeblogoMap.get(key);
			
			String columnKey = nestedWebLogo.getSourceWebLogoStartPosition()+":"+nestedWebLogo.getSourceWebLogoEndPosition();
			
			int numberOfWeblogoInColumn = 1;
			
			if(columnGroupMap.containsKey(columnKey)){
				numberOfWeblogoInColumn = columnGroupMap.get(columnKey);
			}else{
				System.out.println("Error....");
				break;
			}

			numberOfWeblogoInColumn = columnGroupMap.get(columnKey);
			int rankOfWebLogoInColumn = rankOfWebLogoInColumnMap.get(key);
			
			float startYPosition = bottomLeftYPosition - ((rankOfWebLogoInColumn-1) * (maximumHeight / numberOfWeblogoInColumn));
			
			
			
			float startXPosition = 	bottomLeftXPosition  +  ( (nestedWebLogo.getSourceWebLogoStartPosition()-1) * (maximumWidth/columnList.size()));
			
			float newMaximumWidth = ( (nestedWebLogo.getSourceWebLogoEndPosition()-nestedWebLogo.getSourceWebLogoStartPosition()+1) * (maximumWidth/columnList.size()));
			float newMaximumHeight = ((1) * (maximumHeight / numberOfWeblogoInColumn));
			
			drawWebLogo(graphics2DObject, nestedWebLogo.getSourceWebLogo().getColumnList(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight, 1, nestedWebLogo.getSourceWebLogo().getColumnList().size());
			
			//__________________________________________________________________________________________________________________________________
			
			
			startXPosition = 	bottomLeftXPosition  +  ( (nestedWebLogo.getTargetWebLogoStartPosition()-1) * (maximumWidth/columnList.size()));
			
			newMaximumWidth = ( (nestedWebLogo.getTargetWebLogoEndPosition()-nestedWebLogo.getTargetWebLogoStartPosition()+1) * (maximumWidth/columnList.size()));

			newMaximumHeight = ((1) * (maximumHeight / numberOfWeblogoInColumn));
			
			drawWebLogo(graphics2DObject, nestedWebLogo.getTargetWebLogo().getColumnList(), startXPosition, startYPosition, newMaximumWidth, newMaximumHeight, 1, nestedWebLogo.getTargetWebLogo().getColumnList().size());
			
		}
		
	}
	
	private void drawBorder(Graphics2D graphics2DObject, LogoBoundry logoBoundry){
		
		Color originalColor = graphics2DObject.getColor();
		
		graphics2DObject.setColor(Color.RED);
		Stroke originalStroke = graphics2DObject.getStroke();
		
		graphics2DObject.setStroke(new BasicStroke(4));
		
		Line2D line = new Line2D.Double(logoBoundry.getLeftXPosition(),logoBoundry.getTopYPosition(),logoBoundry.getLeftXPosition(),logoBoundry.getBottomYPosition());
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getLeftXPosition(),logoBoundry.getBottomYPosition(),logoBoundry.getRightXPosition(),logoBoundry.getBottomYPosition());		
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getRightXPosition(),logoBoundry.getBottomYPosition(),logoBoundry.getRightXPosition(),logoBoundry.getTopYPosition());		
		graphics2DObject.draw(line);
		
		line = new Line2D.Double(logoBoundry.getRightXPosition(),logoBoundry.getTopYPosition(),logoBoundry.getLeftXPosition(),logoBoundry.getTopYPosition());		
		graphics2DObject.draw(line);
		
		graphics2DObject.setStroke(originalStroke);
		graphics2DObject.setColor(originalColor);
	}
	
	private void drawXAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int topLeftYPosition, int bottomRightXPosition, int bottomRightYPosition){
		Line2D line = new Line2D.Double(bottomLeftXPosition,topLeftYPosition,bottomRightXPosition,bottomRightYPosition);		
		graphics2DObject.draw(line);
	}
	
	private void drawYAxis(Graphics2D graphics2DObject, int bottomLeftXPosition, int bottomLeftYPosition, int topLeftXPosition, int topLeftYPosition){
		Line2D line = new Line2D.Double(bottomLeftXPosition,bottomLeftYPosition,topLeftXPosition,topLeftYPosition);		
		graphics2DObject.draw(line);
	}

	public float getHorizontalScalingFactor(float maximumWidth, int numberOfColumn){
		if(numberOfColumn != 0){
			return maximumWidth/numberOfColumn * 9;
		}else{
			return 1f;
		}
		
	}
	
	private LogoBoundry getLogoBoundry(ArrayList<WeblogoColumn> columnList, float leftXPosition, float bottomYPosition, float maximumWidth, float maximumHeight){
		
		LogoBoundry boundry = new LogoBoundry();
		
		float rightXPosition = leftXPosition;
		float topYPosition = bottomYPosition;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			Map<String, Double> charactersInColumnMap = weblogoColumn.getCharactersMap();

			Set<String> characters = charactersInColumnMap.keySet();
			
			

			float totalHeight = 0;
			
			for (String character : characters) {
				
				Double characterHeight = charactersInColumnMap.get(character);
				
				if(characterHeight == null){
					continue;
				}
				
				totalHeight += characterHeight ;
				
			}
			
			float heightFactor = maximumHeight/totalHeight;
			heightFactor = heightFactor / 8.6f;
			
			float currentColumnSize = bottomYPosition;
			
			for (String character : characters) {
				
				double characterHeight = charactersInColumnMap.get(character);
				
				characterHeight *= heightFactor;
				
				currentColumnSize -= characterHeight * 8.6;	
			
			}
			
			if(currentColumnSize < topYPosition){
				topYPosition = currentColumnSize;
			}
			
			rightXPosition  += (maximumWidth/(columnList.size()));
		}
		
		boundry.setLeftXPosition(leftXPosition);
		boundry.setRightXPosition(rightXPosition);
		boundry.setBottomYPosition(bottomYPosition);
		boundry.setTopYPosition(topYPosition);
		
		return boundry;
	}
	
	private void drawWebLogo(Graphics2D graphics2DObject, ArrayList<WeblogoColumn> columnList, float bottomLeftXPosition, float bottomLeftYPosition, float maximumWidth, float maximumHeight, int startPosition, int endPosition){
		
		LogoBoundry logoBoundry = getLogoBoundry(columnList, bottomLeftXPosition , bottomLeftYPosition  , maximumWidth, maximumHeight);

		int count = 1;
		
		for (WeblogoColumn weblogoColumn : columnList) {
			
			if(startPosition<=count && count<=endPosition){
				drawColumn(graphics2DObject, weblogoColumn, bottomLeftXPosition, bottomLeftYPosition, maximumWidth/(columnList.size() * 9) , maximumHeight);
			}					
			count++;
			bottomLeftXPosition  += (maximumWidth/(columnList.size()));
			
		}			
		drawBorder(graphics2DObject, logoBoundry);
	}
	
	private void drawColumn(Graphics2D graphics2DObject, WeblogoColumn column, float bottomLeftXPosition, float bottomLeftYPosition, float horizontalScalingFactor, float maximumHeight){
		
		Map<String, Double> charactersInColumnMap = column.getCharactersMap();
		
		Set<String> characters = charactersInColumnMap.keySet();
		
		
		
		float totalHeight = 0;
		
		for (String character : characters) {
			
			double characterHeight = charactersInColumnMap.get(character);
			
			totalHeight += characterHeight ;
			
		}
		
		float heightFactor = maximumHeight/totalHeight;
		heightFactor = heightFactor / 8.6f;
		
		for (String character : characters) {
			
			double characterHeight = charactersInColumnMap.get(character);
			
			characterHeight *= heightFactor;
			
			AffineTransform attributeTransform = AffineTransform.getScaleInstance(horizontalScalingFactor, characterHeight);
			
			drawChar(graphics2DObject, attributeTransform, character, bottomLeftXPosition, bottomLeftYPosition);
			
			bottomLeftYPosition -= characterHeight * 8.6;	
		
		}
	}
	
	@Override
	   public Dimension getPreferredSize() {
	      return new Dimension(preferredSizeWidth, preferredSizeHeight);
	   }

	
	private void drawChar(Graphics2D graphics2DObject, AffineTransform attributeTransform , String ch, float bottomLeftXPosition, float bottomLeftYPosition){
		
		AttributedString attributeString = new AttributedString(ch);
		
		if(attributeTransform != null){
			attributeString.addAttribute(TextAttribute.TRANSFORM,attributeTransform);
		}		
		
		graphics2DObject.setColor(Color.BLACK);
		
		if(ch.equals("A")){
			graphics2DObject.setColor(Color.GREEN);	
		}
		
		if(ch.equals("T")){
			graphics2DObject.setColor(Color.RED);	
		}
		
		if(ch.equals("G")){
			graphics2DObject.setColor(Color.YELLOW);	
		}
		
		if(ch.equals("C")){
			graphics2DObject.setColor(Color.BLUE);
		}
		
		graphics2DObject.drawString(attributeString.getIterator(), bottomLeftXPosition, bottomLeftYPosition);

		
	}

	
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Nested Web Logo");
		
		Weblogo3DDrawer charTest = new Weblogo3DDrawer();
		charTest.init();
		
		JScrollPane pane = new JScrollPane(charTest,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setAutoscrolls(true);
		
		frame.add(pane,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		frame.setSize(new Dimension(600, 600));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void getFastaFilePathFromInputBox(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "fasta", "fasta","txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getAbsolutePath());
	    }
	    
	    Utilities utilities = new Utilities();
	    
	    String path = chooser.getSelectedFile().getAbsolutePath();
	    path = path.replace('\\', '/');
	    
	    System.out.println("Modified path: "+path);
	    webLogoFilePath = path;
	    WeblogoDataStructure dataStructure =  utilities.getWeblogoDS(chooser.getSelectedFile().getAbsolutePath());
	    columnList = dataStructure.getColumnList();
	    this.clearNestedWebLogoMap();
	    this.repaint();
	}
}