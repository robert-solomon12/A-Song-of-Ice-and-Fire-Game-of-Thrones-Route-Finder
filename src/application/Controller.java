package application;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.plaf.metal.OceanTheme;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

//import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Controller {

	@FXML
	private Slider slider;
	@FXML
	private ScrollPane map_scrollpane;
	@FXML
	private MenuButton map_pin1,map_pin2;
	@FXML
	private ImageView imageView;
	@FXML
	private Pane mypane;

	@FXML
	private Button establishB;

	public DistancePath d;

	@FXML
	private ComboBox<String> select1;

	@FXML
	private ComboBox<String> select2;

	@FXML
	private ComboBox<String> cityavoidance;

	@FXML
	private ComboBox<String> cityToInclude;
	
	@FXML
	private Label pathVal;
	
	Group zoomGroup;

	private City[] cityList  = new City[42];
	@SuppressWarnings("unchecked")
	private Node<City> nodelist [] = new Node[42];

	public List<Node<City>> path;
	public List<List<Node<City>>> allPaths;

	@FXML
	public void initialize() {
		slider.setMin(0.5);
		slider.setMax(1.5);
		slider.setValue(1.0);
		slider.valueProperty().addListener((o,oldValue,newValue) -> zoom((Double)newValue));

		select1.getItems().addAll("Castle Black","The Dreadfort","Barrow Town","Harrenhal","King's Landing","Highgarden","Storm's End","Oldtown","Sunspear","Winterfell","Bear Island","White Harbor","Moat Cailin","The Twins","Pyke","The Crag","Riverrun","The Eyrie","Gulltown","Faircastle","Casterly Rock","Lannisport","Dragonstone","Tyrosh","Braavos","Lorath","Norvos","Pentos","Choyan Drohe","Ny Sar","Myr","QOHOR","AR NOY","THE SORROWS","SELHORYS", 	"VALYSAR", "VOLON THERYS","SAR MELL","VOLANTIS","VAES KHADOKH","SAATH");

		select2.getItems().addAll("Castle Black","The Dreadfort","Barrow Town","Harrenhal","King's Landing","Highgarden","Storm's End","Oldtown","Sunspear","Winterfell","Bear Island","White Harbor","Moat Cailin","The Twins","Pyke","The Crag","Riverrun","The Eyrie","Gulltown","Faircastle","Casterly Rock","Lannisport","Dragonstone","Tyrosh","Braavos","Lorath","Norvos","Pentos","Choyan Drohe","Ny Sar","Myr","QOHOR","AR NOY","THE SORROWS","SELHORYS", 	"VALYSAR", "VOLON THERYS","SAR MELL","VOLANTIS","VAES KHADOKH","SAATH");

		cityToInclude.getItems().addAll("Castle Black","The Dreadfort","Barrow Town","Harrenhal","King's Landing","Highgarden","Storm's End","Oldtown","Sunspear","Winterfell","Bear Island","White Harbor","Moat Cailin","The Twins","Pyke","The Crag","Riverrun","The Eyrie","Gulltown","Faircastle","Casterly Rock","Lannisport","Dragonstone","Tyrosh","Braavos","Lorath","Norvos","Pentos","Choyan Drohe","Ny Sar","Myr","QOHOR","AR NOY","THE SORROWS","SELHORYS", 	"VALYSAR", "VOLON THERYS","SAR MELL","VOLANTIS","VAES KHADOKH","SAATH");


		// Wrap scroll content in a Group so ScrollPane re-computes scroll bars
		Group contentGroup = new Group();
		zoomGroup = new Group();
		contentGroup.getChildren().add(zoomGroup);
		zoomGroup.getChildren().add(map_scrollpane.getContent());
		map_scrollpane.setContent(contentGroup);
		map_pin1.setVisible(false);
		map_pin2.setVisible(false);


		select1.getSelectionModel().selectedItemProperty().addListener((o,oldValue,newValue) -> {
			if(newValue != null) {
				for(int z =0 ; z < cityList.length;z++) {
					if(cityList[z].getCityName().equals(newValue)) {
						map_pin1.setLayoutX(cityList[z].getX()-24);
						map_pin1.setLayoutY(cityList[z].getY()-60);
						map_pin1.setVisible(true);
						break;
					}
				}
			}
		});

		select2.getSelectionModel().selectedItemProperty().addListener((o,oldValue,newValue) -> {
			if(newValue != null) {
				for(int z =0 ; z < cityList.length;z++) {
					if(cityList[z].getCityName().equals(newValue)) {
						map_pin2.setLayoutX(cityList[z].getX()-24);
						map_pin2.setLayoutY(cityList[z].getY()-60);
						map_pin2.setVisible(true);
						break;
					}
				}
			}
		});

		main();
		System.out.println(mypane.getChildren().size());
	}

	

	@FXML
	public void zoomIn(ActionEvent event) {
		//	    System.out.println("airportapp.Controller.zoomIn");
		double sliderVal = slider.getValue();
		slider.setValue(sliderVal += 0.1);
	}

	@FXML
	public void zoomOut(ActionEvent event) {
		//	    System.out.println("airportapp.Controller.zoomOut");
		double sliderVal = slider.getValue();
		slider.setValue(sliderVal + -0.1);
	}


	private void zoom(double scaleValue) {
		//	    System.out.println("airportapp.Controller.zoom, scaleValue: " + scaleValue);
		double scrollH = map_scrollpane.getHvalue();
		double scrollV = map_scrollpane.getVvalue();
		zoomGroup.setScaleX(scaleValue);
		zoomGroup.setScaleY(scaleValue);
		map_scrollpane.setHvalue(scrollH);
		map_scrollpane.setVvalue(scrollV);
	}

	@FXML
	public void pinMove()
	{
		imageView.setOnMouseDragged(e -> {
			System.out.println("X:"+ e.getX()+ "Y:"+e.getY());
			 //move the pin and set it's info
//				map_pin1.setLayoutX(e.getX()-24);
//				map_pin1.setLayoutY(e.getY()-60);
		}); 
	}


	public void main ()
	{			
		/*
		 * City object is created here taking in the Attributes of a String and the x,y integers
		 */

		City a = new City("Castle Black", 963.635818309064, 530.110264371092);
		City b = new City("The Dreadfort", 1026.68305627889, 808.213014253298);
		City c = new City("Winterfell",820,850);
		City d = new City("Barrow Town", 670.564197559144, 1027.94592708038);
		City e = new City("White Harbor",930,1050);

		City f = new City("Moat Cailin",820,1100);

		City g = new City("The Twins",790,1320);

		City h = new City("The Eyrie",1040,1390);
		City i = new City("Riverrun",750,1490);
		City j = new City("Harrenhal", 910.50243570366, 1538.13039134556);
		City k = new City("Gulltown",1210,1430);
		City l = new City("The Crag", 550, 1510);
		City m = new City("Pyke",520, 1420);
		City n = new City("Faircastle",500,1550);
		City o = new City("Casterly Rock",580,1590);
		City p = new City("Lannisport",520,1650);
		City q = new City("Highgarden",633.333333333333, 1939.33333333333);
		City r = new City("Storm's End", 1130, 1880);
		City s = new City("Oldtown", 520, 2080);
		City t = new City("Sunspear", 1208, 2200);
		City u = new City("Hannisport", 518.40000000, 1651.19999999999);
		City v = new City("Bear Island",640,640);
		City w = new City("King's Landing", 978.666666666666, 1708.66666666666);
		City x = new City("Dragonstone",1190,1590);	
		City y = new City("Braavos",1450,1260);
		City z = new City("Lorath",1650,1310);

		/*
		 * STILL LEFT TO DESIGN THOSE ROUTES
		 */
		City aa = new City("Tyrosh",1350,1990);
		City bb = new City("Norvos",1720,1540);
		City cc = new City("Pentos",1460,1690);
		City dd = new City("Choyan Drohe",1600,1660);
		City ee = new City("Ny Sar",1750,1740);
		City ff = new City("Myr",1550,1960);
		
		City gg = new City("QOHOR",	1970,1690);
		City hh = new City("AR NOY",1870,1780);
		City ii = new City("THE SORROWS",1800,	1940);
		City jj = new City("SELHORYS",1830,2070);
		City kk = new City("VALYSAR",1820,2130);
		City ll = new City("VOLON THERYS	",1850,2190);
		City mm = new City("SAR MELL", 1860, 2170);
		City nn = new City("VAES KHADOKH", 2190, 1680);
		City oo = new City("SAATH", 2240, 1370);
		City pp = new City("VOLANTIS",1910,2210);
		
		
		/*
		 * ArrayList for the city objects 
		 */

		cityList[0] = a;
		cityList[1] = b;
		cityList[2] = c;
		cityList[3] = d;
		cityList[4] = e;
		cityList[5] = f;
		cityList[6] = g;
		cityList[7] = h;
		cityList[8] = i;
		cityList[9] = j;

		cityList[10] = k;
		cityList[11] = l;
		cityList[12] = m;
		cityList[13] = n;
		cityList[14] = o;
		cityList[15] = p;
		cityList[16] = q;

		cityList[17] = r;
		cityList[18] = s;
		cityList[19] = t;
		cityList[20] = u;
		cityList[21] = v;

		cityList[22] = w;
		cityList[23] = x;
		cityList[24] = y;
		cityList[25] = z;

		cityList[26] = aa;
		cityList[27] = bb;
		cityList[28] = cc;
		cityList[29] = dd;
		cityList[30] = ee;
		cityList[31] = ff;
		cityList[32] = gg;
		cityList[33] = hh;
		cityList[34] = ii;
		cityList[35] = jj;
		cityList[36] = kk;
		cityList[37] = ll;
		cityList[38] = mm;
		cityList[39] = nn;
		cityList[40] = oo;
		cityList[41] = pp;


		Node<City> a1 = new Node<City>(a);
		Node<City> b1 = new Node<City>(b);
		Node<City> c1 = new Node<City>(c);
		Node<City> d1 = new Node<City>(d);
		Node<City> e1 = new Node<City>(e);
		Node<City> f1 = new Node<City>(f);
		Node<City> g1 = new Node<City>(g);


		Node<City> h1 = new Node<City>(h);
		Node<City> i1 = new Node<City>(i);
		Node<City> j1 = new Node<City>(j);

		Node<City> k1 = new Node<City>(k);
		Node<City> l1 = new Node<City>(l);
		Node<City> m1 = new Node<City>(m);
		Node<City> n1 = new Node<City>(n);
		Node<City> o1 = new Node<City>(o);
		Node<City> p1 = new Node<City>(p);
		Node<City> q1 = new Node<City>(q);

		Node<City> r1 = new Node<City>(r);
		Node<City> s1 = new Node<City>(s);
		Node<City> t1 = new Node<City>(t);
		Node<City> u1 = new Node<City>(u);
		Node<City> v1 = new Node<City>(v);
		Node<City> w1 = new Node<City>(w);
		Node<City> x1 = new Node<City>(x);

		Node<City> y1 = new Node<City>(y);
		Node<City> z1 = new Node<City>(z);

		Node<City> a2 = new Node<City>(aa);
		Node<City> b2 = new Node<City>(bb);
		Node<City> c2 = new Node<City>(cc);
		Node<City> d2 = new Node<City>(dd);
		Node<City> e2 = new Node<City>(ee);
		Node<City> f2 = new Node<City>(ff);
		
		Node<City> g2 = new Node<City>(gg);
		Node<City> h2 = new Node<City>(hh);
		Node<City> i2 = new Node<City>(ii);
		Node<City> j2 = new Node<City>(jj);
		Node<City> k2 = new Node<City>(kk);
		Node<City> l2 = new Node<City>(ll);
		Node<City> m2 = new Node<City>(mm);
		Node<City> n2 = new Node<City>(nn);
		Node<City> o2 = new Node<City>(oo);
		Node<City> p2 = new Node<City>(pp);

		nodelist [0] = a1;
		nodelist [1] = b1;
		nodelist [2] = c1;
		nodelist [3] = d1;
		nodelist [4] = e1;
		nodelist [5] = f1;
		nodelist [6] = g1;
		nodelist [7] = h1;
		nodelist [8] = i1;
		nodelist [9] = j1;
		nodelist [10] = k1;
		nodelist [11] = l1;
		nodelist [12] = m1;
		nodelist [13] = n1;
		nodelist [14] = o1;
		nodelist [15] = p1;
		nodelist [16] = q1;
		nodelist [17] = r1;
		nodelist [18] = s1;
		nodelist [19] = t1;
		nodelist [20] = u1;
		nodelist [21] = v1;
		nodelist [22] = w1;
		nodelist [23] = x1;
		nodelist [24] = y1;
		nodelist [25] = z1;
		nodelist [26] = a2;
		nodelist [27] = b2;
		nodelist [28] = c2;
		nodelist [29] = d2;
		nodelist [30] = e2;
		nodelist [31] = f2;
		nodelist [32] = g2;
		nodelist [33] = h2;
		nodelist [34] = i2;
		nodelist [35] = j2;
		nodelist [36] = k2;
		nodelist [37] = l2;
		nodelist [38] = m2;
		nodelist [39] = n2;
		nodelist [40] = o2;
		nodelist [41] = p2;

		//Cities connected in order here\\

		v1.connectToNodeUndirected(a1,9,7,6);
		v1.connectToNodeUndirected(d1,6, 5, 6);
		v1.connectToNodeUndirected(c1, 6,5,7);
		a1.connectToNodeUndirected(b1,5,1,2);
		a1.connectToNodeUndirected(v1,9,7,6);
		b1.connectToNodeUndirected(c1,10,5,5);
		b1.connectToNodeUndirected(d1,10,5,5);
		b1.connectToNodeUndirected(e1,10,5,5);
		b1.connectToNodeUndirected(y1, 8, 6,4);
		c1.connectToNodeUndirected(d1,6,1,1);
		c1.connectToNodeUndirected(e1,6,1,1);
		d1.connectToNodeUndirected(e1,3,1,1);
		d1.connectToNodeUndirected(f1, 5, 4, 5);
		e1.connectToNodeUndirected(f1,13,4,5);
		e1.connectToNodeUndirected(y1,8,6,5);
		f1.connectToNodeUndirected(g1,13,4,5);
		y1.connectToNodeUndirected(z1,4,5,6);
		g1.connectToNodeUndirected(i1,13,4,5);
		g1.connectToNodeUndirected(m1, 5, 6, 5);
		g1.connectToNodeUndirected(j1,13,4,5);
		g1.connectToNodeUndirected(h1,6,4,5);
		z1.connectToNodeUndirected(b2,6,5,4);
		z1.connectToNodeUndirected(o2,8,4,5);
		i1.connectToNodeUndirected(j1,13,4,5);
		i1.connectToNodeUndirected(l1,4,2,2);
		i1.connectToNodeUndirected(m1,5,1,1);
		i1.connectToNodeUndirected(o1,13,4,5);
		i1.connectToNodeUndirected(w1,8,1,1);
		j1.connectToNodeUndirected(o1,15,3,5);
		j1.connectToNodeUndirected(w1,4,2,2);
		j1.connectToNodeUndirected(x1,5,4,5);
		h1.connectToNodeUndirected(k1,5,4,4);
		h1.connectToNodeUndirected(j1,5,4,4);
		b2.connectToNodeUndirected(e2,5,4,3);
		b2.connectToNodeUndirected(g2,5,4,4);
		l1.connectToNodeUndirected(n1,13,4,5);
		l1.connectToNodeUndirected(o1,13,4,5);
		m1.connectToNodeUndirected(n1,13,4,5);	
		o1.connectToNodeUndirected(p1,12,3,5);
		w1.connectToNodeUndirected(r1,8,4,4);
		x1.connectToNodeUndirected(c2,6,5,5);
		x1.connectToNodeUndirected(w1,13,4,5);
		x1.connectToNodeUndirected(y1,8,6,7);
		x1.connectToNodeUndirected(a2, 7, 4, 6);
		k1.connectToNodeUndirected(y1,4,5,5);
		k1.connectToNodeUndirected(c2, 6, 4, 4);
		k1.connectToNodeUndirected(a2, 7, 4, 6);
		e2.connectToNodeUndirected(f2,6,4,5);
		e2.connectToNodeUndirected(h2, 3, 5, 3);
		//----------------------------------------------
		//-----------IF YOU WANT TO ADD MORE CITIES===\\
		//---------------------------------------------
		g2.connectToNodeUndirected(n2,5,4,3);
		g2.connectToNodeUndirected(h2, 4, 5, 4);
		n1.connectToNodeUndirected(o1,18,6,5);
		p1.connectToNodeUndirected(w1,13,4,5);
		p1.connectToNodeUndirected(q1, 7, 5,4);
		r1.connectToNodeUndirected(a2,5,4,5);
		r1.connectToNodeUndirected(q1,5,3,4);
		q1.connectToNodeUndirected(s1, 6, 4, 5);
		r1.connectToNodeUndirected(s1,13,4,5);
		c2.connectToNodeUndirected(d2,3,5,4);
		f2.connectToNodeUndirected(e2,6,4,5);
		f2.connectToNodeUndirected(i2, 5, 4, 4);
		f2.connectToNodeUndirected(j2,6,3,5);
		f2.connectToNodeUndirected(k2, 6, 3,5);
		f2.connectToNodeUndirected(l2,7,4,6);
		i2.connectToNodeUndirected(j2, 4, 5, 3);
		j2.connectToNodeUndirected(k2, 3, 2, 3);
		k2.connectToNodeUndirected(m2, 2, 1, 1);
		k2.connectToNodeUndirected(l2, 3, 1, 1);
		m2.connectToNodeUndirected(l2, 1,1,1);
		l2.connectToNodeUndirected(p2, 3, 4, 3);
		h2.connectToNodeUndirected(i2,5,5,4);
		a2.connectToNodeUndirected(f2,4,5,6);
		s1.connectToNodeUndirected(t1,13,4,5);
		d2.connectToNodeUndirected(b2,5,6,5);
		d2.connectToNodeUndirected(f2,4,3,3);
		t1.connectToNodeUndirected(a2,4,5,4);
		n2.connectToNodeUndirected(o2, 6, 4, 3);
		
		//======CONNECTIONS ABOVER ARE ARRANGED IN ORDER\\
		
		traverseGraphDepthFirst(a1,null);
	}

	public void generatingSinglePathP() {
		if(mypane.getChildren().size()>5)
		{
			mypane.getChildren().remove(5,mypane.getChildren().size());
		}
		Node<City> sourc = new Node<City>();
		City dest = new City("", 0, 0);
		//String string = "";

		for(int i = 0;i<nodelist.length;i++) {
			if(select1.getSelectionModel().getSelectedItem().equals(nodelist[i].node.getCityName())) {
				sourc =nodelist[i];
			}
		}

		for(int i = 0;i<cityList.length;i++) {
			if(select2.getSelectionModel().getSelectedItem().equals(cityList[i].getCityName())) {
				dest = cityList[i];

			}
		}

		System.out.println("--------------------------------------------------------------");
		List<Node<City>> path=findPathDepthFirst(sourc,null,dest);
		for(Node<City> n : path)
			//System.out.println(n.node.getCityName());
			cityavoidance.getItems().addAll(n.node.getCityName());


		if(path != null) {
			for(int i=0;i<path.size()-1;i++) {
				drawP(path.get(i).node.x,path.get(i).node.y,path.get(i+1).node.x,path.get(i+1).node.y);

			}
		}

	}
	
	
	/*
	 * Function that deals with the city to avoid when the cityNsme is past into the the Combobox
	 */
	
	public void wayPointFunc() {
		if(mypane.getChildren().size()>3)
			mypane.getChildren().remove(3, mypane.getChildren().size());
		String cityToAvoid = "";
		cityToAvoid = cityavoidance.getSelectionModel().getSelectedItem();
		List<Node<City>> newPath = new ArrayList<>();
		newPath = null;
		boolean flag = true;
		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

		System.out.println(cityToAvoid);
		System.out.println();

		for(int i= 0;i<allPaths.size()-1;i++) {
			flag =true;
			for(int k =0;k<allPaths.get(i).size()-1;k++) {
				if(allPaths.get(i).get(k).node.getCityName().equals(cityToAvoid)) {
					flag=false;
					break;
				}
			}
			if(flag) {
				newPath = allPaths.get(i);
				break;

			}
		}

		System.out.println();
		System.out.println();

		if(newPath==null)
			newPath = path;
		System.out.println(newPath.size());
		for(int i= 0;i<newPath.size()-1;i++)
			System.out.println(newPath.get(i).node.getCityName());


		if( newPath != null) {
			for(int i=0;i<newPath.size()-1;i++) {
				drawP(newPath.get(i).node.getX(),newPath.get(i).node.getY(),newPath.get(i+1).node.getX(),newPath.get(i+1).node.getY());
			}
		}

		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	}
	
	public void wayPointIncludeFunc() {
			if(mypane.getChildren().size()>3)
				mypane.getChildren().remove(3, mypane.getChildren().size());
			String waypointCity = "";
			waypointCity = cityavoidance.getSelectionModel().getSelectedItem();
			List<Node<City>> newPath = new ArrayList<>();
			newPath = null;
			boolean flag = false;
			System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

			System.out.println(waypointCity);
			System.out.println();
			System.out.println();

			for(int i= 0;i<allPaths.size()-1;i++) {
				flag =false;
				for(int k =0;k<allPaths.get(i).size()-1;k++) {
					if(allPaths.get(i).get(k).node.getCityName().equals(waypointCity)) {
						flag=true;
						break;
					}
				}
				if(flag) {
					newPath = allPaths.get(i);
					break;

				}
			}

			System.out.println();
			System.out.println();

			if(newPath==null)
				newPath = path;
			System.out.println(newPath.size());
			for(int i= 0;i<newPath.size()-1;i++)
				System.out.println(newPath.get(i).node.getCityName());


			if( newPath != null) {
				for(int i=0;i<newPath.size()-1;i++) {
					drawP(newPath.get(i).node.getX(),newPath.get(i).node.getY(),newPath.get(i+1).node.getX(),newPath.get(i+1).node.getY());
				}
			}

			System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}


	public  void generatingallpath() {
		if(mypane.getChildren().size()>5)
		{
			mypane.getChildren().remove(5,mypane.getChildren().size());
		}
		Node<City> sourc = new Node<City>();
		City dest = new City("", 0, 0);
		//String string = "";

		for(int i = 0;i<nodelist.length;i++) {
			if(select1.getSelectionModel().getSelectedItem().equals(nodelist[i].node.getCityName())) {
				sourc =nodelist[i];
			}
		}

		for(int i = 0;i<cityList.length;i++) {
			if(select2.getSelectionModel().getSelectedItem().equals(cityList[i].getCityName())) {
				dest = cityList[i];
			}
		}

		allPaths=findAllPathsDepthFirst(sourc,null,dest);
		int pCount=1;
		for(List<Node<City>> p : allPaths) {
		System.out.println("\nPath "+(pCount++)+"\n--------");
			for(Node<City> n : p) {
				System.out.println(n.node.getCityName());
			cityavoidance.getItems().addAll(n.node.getCityName());
			
		}
			
			 String myPathVal = Integer.toString(pCount);
	   	    	 
	    	 pathVal.setText(myPathVal);
	    	 pathVal.setVisible(true);
		}
		

		for(List<Node<City>> pp : allPaths ) {

			if(pp != null) {

				for(int i=0;i<pp.size()-1;i++) {
					drawP(pp.get(i).node.x,pp.get(i).node.y,pp.get(i+1).node.x,pp.get(i+1).node.y);
				}

			}
		}

	}

	public void clearRoutes() {
		if(mypane.getChildren().size()>2)
		{
			mypane.getChildren().remove(4,mypane.getChildren().size());
		}
	}

	public static List<List<Node<City>>> findAllPathsDepthFirst(Node<City> from, List<Node<?>> encountered, City lookingfor){
		List<List<Node<City>>> result=null, temp2;
		if(from.node.equals(lookingfor)) { //Found it
			List<Node<City>> temp=new ArrayList<>(); //Create new single solution path list
			temp.add(from); //Add current node to the new single path list
			result=new ArrayList<>(); //Create new "list of lists" to store path permutations
			result.add(temp); //Add the new single path list to the path permutations list
			return result; //Return the path permutations list
		}
		if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from); //Add current node to encountered list
		for(Edge ed : from.adjList){
			if(!encountered.contains(ed.destNode)) {
				temp2=findAllPathsDepthFirst(ed.destNode,new ArrayList<>(encountered),lookingfor); //Use clone of encountered list
				//for recursive call!
				if(temp2!=null) { //Result of the recursive call contains one or more paths to the solution node
					for(List<Node<City>> x : temp2) //For each partial path list returned
						x.add(0,from); //Add the current node to the front of each path list
					if(result==null) result=temp2; //If this is the first set of solution paths found use it as the result
					else result.addAll(temp2); //Otherwise append them to the previously found paths
				}

			}

		}
		return result;
	}


	public static List<Node<City>> findPathDepthFirst(Node<City> from, List<Node<City>> encountered, City lookingfor){
		List<Node<City>> result;
		if(from.node.equals(lookingfor)) { //Found it
			result=new ArrayList<>(); //Create new list to store the path info (any List implementation could be used)
			result.add(from); //Add the current node as the only/last entry in the path list
			return result; //Return the path list
		}
		if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from);
		for(Node<City> adjNode : from.nodeListNode)
			if(!encountered.contains(adjNode)) {
				result=findPathDepthFirst(adjNode,encountered,lookingfor);
				if(result!=null) { //Result of the last recursive call contains a path to the solution node
					result.add(0,from); //Add the current node to the front of the path list
					return result; //Return the path list
				}
			}
		return null;
	}
	
	

	/*
	 * Algorithm that generates the shortest path from the Source node to Destination node 	
	 */
	
	public void shortestPath()
	{
		if(mypane.getChildren().size()>5)
		{
			mypane.getChildren().remove(5,mypane.getChildren().size());
		}
		Node<City> sourc = new Node<City>();
		City dest = new City("", 0, 0);


		for(int i = 0;i<nodelist.length;i++) {
			if(select1.getSelectionModel().getSelectedItem().equals(nodelist[i].node.getCityName())) {
				sourc =nodelist[i];
			}
		}

		System.out.println("Coming from : " + sourc.node.getCityName());

		for(int i = 0;i<cityList.length;i++) {
			if(select2.getSelectionModel().getSelectedItem().equals(cityList[i].getCityName())) {
				dest = cityList[i];
			}
		}

		System.out.println("Going to : " + dest.getCityName());
		System.out.println("------------------------------------------");

		DistancePath distancepath = d.findShortestPathDijkstra(sourc, dest);

		for(Node<City> n : distancepath.pathList) {
			System.out.println(n.node.getCityName()); 
		}

		if(distancepath.pathList != null) {

			for(int i=0;i<distancepath.pathList.size()-1;i++) {
				drawP(distancepath.pathList.get(i).node.x,distancepath.pathList.get(i).node.y,distancepath.pathList.get(i+1).node.x,distancepath.pathList.get(i+1).node.y);
			}
		}
		System.out.println("\nThe total path distance is: "+distancepath.pathDistance); 

	}


	public void safestPath()
	{
		if(mypane.getChildren().size()>5)
		{
			mypane.getChildren().remove(5,mypane.getChildren().size());
		}

		Node<City> sourc = new Node<City>();
		City dest = new City("", 0, 0);


		for(int i = 0;i<nodelist.length;i++) {
			if(select1.getSelectionModel().getSelectedItem().equals(nodelist[i].node.getCityName())) {
				sourc =nodelist[i];
			}
		}

		System.out.println("Coming from : " + sourc.node.getCityName());

		for(int i = 0;i<cityList.length;i++) {
			if(select2.getSelectionModel().getSelectedItem().equals(cityList[i].getCityName())) {
				dest = cityList[i];
			}

		}

		System.out.println("Going to : " + dest.getCityName());
		System.out.println("------------------------------------------");

		DistancePath safetypath = d.findSafestPathDijkstra(sourc, dest);

		for(Node<City> n : safetypath.pathList) {
			System.out.println(n.node.getCityName()); 
		}

		if(safetypath.pathList != null) {

			for(int i=0;i<safetypath.pathList.size()-1;i++) {
				drawP(safetypath.pathList.get(i).node.x,safetypath.pathList.get(i).node.y,safetypath.pathList.get(i+1).node.x,safetypath.pathList.get(i+1).node.y);
			}
		}
		System.out.println("\nThe total path safety is: "+safetypath.pathSafety); 

	}


	public void easiestPath()
	{
		if(mypane.getChildren().size()>5)
		{
			mypane.getChildren().remove(5,mypane.getChildren().size());
		}
		Node<City> sourc = new Node<City>();
		City dest = new City("", 0, 0);


		for(int i = 0;i<nodelist.length;i++) {
			if(select1.getSelectionModel().getSelectedItem().equals(nodelist[i].node.getCityName())) {
				sourc =nodelist[i];
			}
		}

		System.out.println("Coming from : " + sourc.node.getCityName());

		for(int i = 0;i<cityList.length;i++) {
			if(select2.getSelectionModel().getSelectedItem().equals(cityList[i].getCityName())) {
				dest = cityList[i];
			}
		}

		System.out.println("Going to : " + dest.getCityName());
		System.out.println("------------------------------------------");

		DistancePath easypath = d.findEasiestPathDijkstra(sourc, dest);

		for(Node<City> n : easypath.pathList) {
			System.out.println(n.node.getCityName()); 
		}

		if(easypath.pathList != null) {

			for(int i=0;i<easypath.pathList.size()-1;i++) {
				drawP(easypath.pathList.get(i).node.x,easypath.pathList.get(i).node.y,easypath.pathList.get(i+1).node.x,easypath.pathList.get(i+1).node.y);
			}
		}
		System.out.println("\nThe total path easy is: "+easypath.pathEasy); 
	}


	public void drawP(double fromX, double fromY, double toX, double toY) {
	Line line = new Line(fromX,fromY,toX,toY);
		line.setStroke(Color.DARKBLUE);
		line.setStrokeWidth(3);
//
    	mypane.getChildren().add(line);
	
//		line.getStrokeDashArray().setAll(25d,20d,5d,20d);
//		double maxOffset = line.getStrokeDashArray().stream().reduce(0d, (a, b) -> a + b);
//
//		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,  new KeyValue(line.strokeDashOffsetProperty(),0, Interpolator.LINEAR)), new KeyFrame(Duration.seconds(2), new KeyValue(line.strokeDashOffsetProperty(), maxOffset, Interpolator.LINEAR)));
//		
//		timeline.setCycleCount(timeline.INDEFINITE);
//		timeline.setRate(-1);
//		timeline.play();
//		mypane.getChildren().add(line);
	}

	//	public static void traverseNodeDepthFirstShowingTotalDistance(Node<City> from,List<Node<City>> encountered, int totalDistance)
	//	{

	public static void traverseGraphDepthFirst(Node<City> from, List<Node<City>> encountered ){
		System.out.println(from.node.cityName);
		if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from);
		for(Node<City> adjNode : encountered)
			if(!encountered.contains(adjNode)) traverseGraphDepthFirst(adjNode, encountered );
	}



}
