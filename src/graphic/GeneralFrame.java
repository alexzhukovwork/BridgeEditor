/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;


/**
 *
 * @author Алексей
 */
public class GeneralFrame extends javax.swing.JFrame implements Serializable{

    /**
     * Creates new form GeneralFrame
     */
    private Model model;
    private List<Model> models = new ArrayList<>();
    private int number = 0;
    private Camera camera;
    private final NumberFormat numberMask;
    private IRender render;
    private ArrayList<JSpinner> spinners = new ArrayList<JSpinner>();
    
    
    public GeneralFrame() {
        model = null;
        render = new RenderFill();
        numberMask = new DecimalFormat(); 
        numberMask.setMaximumIntegerDigits(4);
        numberMask.setMinimumIntegerDigits(0);
        initComponents();
        setSpinner();
        setSelectListener();
        setPaint();
        setKeyListener();
        camera = new Camera();
        
        camera.width = 600;
        camera.height = 300;   
        camera.x = 200;
        
        camera.y = 0;
        camera.z = 700;
        camera.d = Math.sqrt(camera.x*camera.x + camera.y*camera.y + camera.z*camera.z);
        jSelectModel.removeAllItems();
        Bridge b = new Bridge(200, 0, 200, "Мост" + models.size());
        b.createModel();
        models.add( b );
        jSelectModel.addItem(b.getName());
        
        b = new Bridge(0, 0, 200, "Мост" + models.size());
        b.createModel();
        models.add( b );
        jSelectModel.addItem(b.getName());
        
        b = new Bridge(0, 0, 1200, "Мост" + models.size());
        b.createModel();
        models.add( b );
        jSelectModel.addItem(b.getName());  
    }
    
    private void setSelectListener(){
        JFrame jFrame = this;
        jSelectModel.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && jSelectModel.getSelectedIndex() > -1 && !models.isEmpty() ){
                    model = models.get( jSelectModel.getSelectedIndex() );
                    getModelParam( jSelectModel.getSelectedIndex() );
                    jFrame.requestFocus();
                    workPanel.repaint();
                }
            }
        });
    }
    
    private void setSpinner(){
        double v = 1;
        double min = 1;
        double max = 10000;
        double step = 1;
        spinners.add(jEWorldX);
        spinners.add(jEWorldY);
        spinners.add(jEWorldZ);
        spinners.add(jEWorldAngleX);
        spinners.add(jEWorldAngleY);
        spinners.add(jEWorldAngleZ);
        spinners.add(jBindingHeight);
        spinners.add(jBridgeLength);
        spinners.add(jBridgeLevel);
        spinners.add(jBridgeWidth);
        spinners.add(jFirstHoleHeight);
        spinners.add(jLowerSupportHeight);
        spinners.add(jLowerSupportThick);
        spinners.add(jLowerSupportWidth);
        spinners.add(jMetalHeight);
        spinners.add(jMetalWidth);
        spinners.add(jSecondHoleHeight);
        spinners.add(jThirdHoleHeight);
        spinners.add(jSupportHeight);
        spinners.add(jSupportThick);
        
        for(JSpinner spinner : spinners){
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(v,min,max,step);
            spinner.setModel(spinnerModel);
            spinner.setEditor(new JSpinner.NumberEditor(spinner, "#0.000"));
        }
        
        min = -10000;
       
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jTargetX.setModel(spinnerModel);
        jTargetX.setEditor(new JSpinner.NumberEditor(jTargetX, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jTargetY.setModel(spinnerModel);
        jTargetY.setEditor(new JSpinner.NumberEditor(jTargetY, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jTargetZ.setModel(spinnerModel);
        jTargetZ.setEditor(new JSpinner.NumberEditor(jTargetZ, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jCameraX.setModel(spinnerModel);
        jCameraX.setEditor(new JSpinner.NumberEditor(jCameraX, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jCameraY.setModel(spinnerModel);
        jCameraY.setEditor(new JSpinner.NumberEditor(jCameraY, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jCameraZ.setModel(spinnerModel);
        jCameraZ.setEditor(new JSpinner.NumberEditor(jCameraZ, "#0.000"));
        
        
        max = 360;
        min = -360;
        step = 0.5;
        v = 0;
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jCameraAngleX.setModel(spinnerModel);
        jCameraAngleX.setEditor(new JSpinner.NumberEditor(jCameraAngleX, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jCameraAngleY.setModel(spinnerModel);
        jCameraAngleY.setEditor(new JSpinner.NumberEditor(jCameraAngleY, "#0.000"));

        
        max = 10;
        min = 0.001;
        step = 0.5;
        v = 1;
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jScaleX.setModel(spinnerModel);
        jScaleX.setEditor(new JSpinner.NumberEditor(jScaleX, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jScaleY.setModel(spinnerModel);
        jScaleY.setEditor(new JSpinner.NumberEditor(jScaleY, "#0.000"));
        spinnerModel = new SpinnerNumberModel(v,min,max,step);
        jScaleZ.setModel(spinnerModel);
        jScaleZ.setEditor(new JSpinner.NumberEditor(jScaleZ, "#0.000"));
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jMenu1 = new javax.swing.JMenu();
        jFrame1 = new javax.swing.JFrame();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        eWorldX3 = new javax.swing.JFormattedTextField();
        jLabel30 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane6 = new javax.swing.JSplitPane();
        workPanel = new javax.swing.JPanel();
        jSelectModel = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        bCreateModel = new javax.swing.JToggleButton();
        bChangeModel = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jSupportThick = new javax.swing.JSpinner();
        jSupportHeight = new javax.swing.JSpinner();
        jFirstHoleHeight = new javax.swing.JSpinner();
        jSecondHoleHeight = new javax.swing.JSpinner();
        jThirdHoleHeight = new javax.swing.JSpinner();
        jBindingHeight = new javax.swing.JSpinner();
        jLowerSupportThick = new javax.swing.JSpinner();
        jLowerSupportHeight = new javax.swing.JSpinner();
        jLowerSupportWidth = new javax.swing.JSpinner();
        jMetalWidth = new javax.swing.JSpinner();
        jMetalHeight = new javax.swing.JSpinner();
        jBridgeLength = new javax.swing.JSpinner();
        jBridgeWidth = new javax.swing.JSpinner();
        jBridgeLevel = new javax.swing.JSpinner();
        jCountRope = new javax.swing.JSpinner();
        jCountHole = new javax.swing.JSpinner();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jSplitPane11 = new javax.swing.JSplitPane();
        jEWorldX = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jSplitPane12 = new javax.swing.JSplitPane();
        jEWorldY = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jSplitPane13 = new javax.swing.JSplitPane();
        jEWorldZ = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jSplitPane14 = new javax.swing.JSplitPane();
        jEWorldAngleX = new javax.swing.JSpinner();
        jLabel29 = new javax.swing.JLabel();
        jSplitPane15 = new javax.swing.JSplitPane();
        jEWorldAngleY = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jSplitPane16 = new javax.swing.JSplitPane();
        jEWorldAngleZ = new javax.swing.JSpinner();
        jLabel32 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jLabel28 = new javax.swing.JLabel();
        jSplitPane17 = new javax.swing.JSplitPane();
        jLabel37 = new javax.swing.JLabel();
        jScaleX = new javax.swing.JSpinner();
        jSplitPane18 = new javax.swing.JSplitPane();
        jLabel38 = new javax.swing.JLabel();
        jScaleY = new javax.swing.JSpinner();
        jSplitPane19 = new javax.swing.JSplitPane();
        jLabel39 = new javax.swing.JLabel();
        jScaleZ = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTargetButton = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jSplitPane2 = new javax.swing.JSplitPane();
        jLabel26 = new javax.swing.JLabel();
        eCamAngleX = new javax.swing.JFormattedTextField();
        jCameraAngleX = new javax.swing.JSpinner();
        jSplitPane3 = new javax.swing.JSplitPane();
        jLabel22 = new javax.swing.JLabel();
        eCameraX = new javax.swing.JFormattedTextField();
        jCameraX = new javax.swing.JSpinner();
        jSplitPane4 = new javax.swing.JSplitPane();
        jLabel35 = new javax.swing.JLabel();
        eCameraY = new javax.swing.JFormattedTextField();
        jCameraY = new javax.swing.JSpinner();
        jSplitPane5 = new javax.swing.JSplitPane();
        jLabel24 = new javax.swing.JLabel();
        eCameraZ = new javax.swing.JFormattedTextField();
        jCameraZ = new javax.swing.JSpinner();
        jSplitPane7 = new javax.swing.JSplitPane();
        jLabel27 = new javax.swing.JLabel();
        eCamAngleY = new javax.swing.JFormattedTextField();
        jCameraAngleY = new javax.swing.JSpinner();
        jSplitPane8 = new javax.swing.JSplitPane();
        jLabel34 = new javax.swing.JLabel();
        jTargetX = new javax.swing.JSpinner();
        jSplitPane9 = new javax.swing.JSplitPane();
        jLabel23 = new javax.swing.JLabel();
        jTargetY = new javax.swing.JSpinner();
        jSplitPane10 = new javax.swing.JSplitPane();
        jLabel36 = new javax.swing.JLabel();
        jTargetZ = new javax.swing.JSpinner();
        jChangeCamera = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jSaveScene = new javax.swing.JMenuItem();
        jLoadScene = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jLabel30.setText("angleX");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout workPanelLayout = new javax.swing.GroupLayout(workPanel);
        workPanel.setLayout(workPanelLayout);
        workPanelLayout.setHorizontalGroup(
            workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        workPanelLayout.setVerticalGroup(
            workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSelectModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Толщина опоры");

        jLabel2.setText("Высота опоры");

        jLabel3.setText("Высота отверстия");

        jLabel4.setText("Высота 3 отверстия");

        jLabel5.setText("Высота 2 отверстия");

        jLabel6.setText("Высота крепления троса");

        jLabel7.setText("Толщина основания опоры");

        jLabel8.setText("Высота основания опоры");

        jLabel9.setText("Ширина основания опоры");

        jLabel10.setText("Ширина металлоконструкций");

        jLabel11.setText("Высота металлоконструкций");

        jLabel12.setText("Длина моста");

        jLabel13.setText("Ширина моста");

        jLabel14.setText("Высота моста");

        jLabel15.setText("Количество тросов");

        jLabel16.setText("Количество отверстий после 2");

        bCreateModel.setText("Создать");
        bCreateModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateModelActionPerformed(evt);
            }
        });

        bChangeModel.setText("Изменить");
        bChangeModel.setMaximumSize(new java.awt.Dimension(75, 23));
        bChangeModel.setMinimumSize(new java.awt.Dimension(75, 23));
        bChangeModel.setPreferredSize(new java.awt.Dimension(75, 23));
        bChangeModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeModelActionPerformed(evt);
            }
        });

        jLabel17.setText("Позиция на сцене");

        jSplitPane11.setRightComponent(jEWorldX);

        jLabel18.setText("X");
        jSplitPane11.setLeftComponent(jLabel18);

        jSplitPane12.setRightComponent(jEWorldY);

        jLabel19.setText("Y");
        jSplitPane12.setLeftComponent(jLabel19);

        jSplitPane13.setRightComponent(jEWorldZ);

        jLabel20.setText("Z");
        jSplitPane13.setLeftComponent(jLabel20);

        jSplitPane14.setRightComponent(jEWorldAngleX);

        jLabel29.setText("angleX");
        jSplitPane14.setLeftComponent(jLabel29);

        jSplitPane15.setRightComponent(jEWorldAngleY);

        jLabel31.setText("angleY");
        jSplitPane15.setLeftComponent(jLabel31);

        jSplitPane16.setRightComponent(jEWorldAngleZ);

        jLabel32.setText("angleZ");
        jSplitPane16.setLeftComponent(jLabel32);

        jLabel28.setText("Масштабирование");

        jLabel37.setText("x");
        jSplitPane17.setLeftComponent(jLabel37);
        jSplitPane17.setRightComponent(jScaleX);

        jLabel38.setText("y");
        jSplitPane18.setLeftComponent(jLabel38);
        jSplitPane18.setRightComponent(jScaleY);

        jLabel39.setText("z");
        jSplitPane19.setLeftComponent(jLabel39);
        jSplitPane19.setRightComponent(jScaleZ);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSplitPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSplitPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSplitPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(filler5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSplitPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSplitPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSplitPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLowerSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLowerSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBindingHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jThirdHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSecondHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFirstHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMetalWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLowerSupportWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMetalHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBridgeLength, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBridgeWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBridgeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCountRope, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCountHole, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filler8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bChangeModel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bCreateModel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSplitPane17)
                                .addGap(18, 18, 18)
                                .addComponent(jSplitPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSplitPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(filler2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFirstHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel5)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSecondHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jThirdHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBindingHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLowerSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLowerSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLowerSupportWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jMetalWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jMetalHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jBridgeLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jBridgeWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jBridgeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(6, 6, 6))
                            .addComponent(jCountRope, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jCountHole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSplitPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSplitPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSplitPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSplitPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSplitPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSplitPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(filler8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSplitPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSplitPane18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSplitPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bChangeModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCreateModel))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel21.setText("Камера");

        jTargetButton.setText("Применить");
        jTargetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTargetButtonActionPerformed(evt);
            }
        });

        jLabel33.setText("Цель камеры");

        jLabel26.setText("angleX");
        jSplitPane2.setLeftComponent(jLabel26);
        jSplitPane2.setRightComponent(eCamAngleX);
        jSplitPane2.setRightComponent(jCameraAngleX);

        jLabel22.setText("X");
        jSplitPane3.setLeftComponent(jLabel22);
        jSplitPane3.setRightComponent(eCameraX);
        jSplitPane3.setRightComponent(jCameraX);

        jLabel35.setText("Y");
        jSplitPane4.setLeftComponent(jLabel35);
        jSplitPane4.setRightComponent(eCameraY);
        jSplitPane4.setRightComponent(jCameraY);

        jLabel24.setText("Z");
        jSplitPane5.setLeftComponent(jLabel24);
        jSplitPane5.setRightComponent(eCameraZ);
        jSplitPane5.setRightComponent(jCameraZ);

        jLabel27.setText("angleY");
        jSplitPane7.setLeftComponent(jLabel27);

        eCamAngleY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCamAngleYActionPerformed(evt);
            }
        });
        jSplitPane7.setRightComponent(eCamAngleY);
        jSplitPane7.setRightComponent(jCameraAngleY);

        jLabel34.setText("X");
        jSplitPane8.setLeftComponent(jLabel34);
        jSplitPane8.setRightComponent(jTargetX);

        jLabel23.setText("Y");
        jSplitPane9.setLeftComponent(jLabel23);
        jSplitPane9.setRightComponent(jTargetY);

        jLabel36.setText("Z");
        jSplitPane10.setLeftComponent(jLabel36);
        jSplitPane10.setRightComponent(jTargetZ);

        jChangeCamera.setText("Применить");
        jChangeCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChangeCameraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSplitPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTargetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jChangeCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSplitPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jSplitPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSplitPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jChangeCamera)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSplitPane8)
                                    .addComponent(jSplitPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jTargetButton))
                            .addComponent(jSplitPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(238, 238, 238)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );

        jScrollPane2.setViewportView(jPanel2);

        jMenu2.setText("Файл");

        jSaveScene.setText("Сохранить сцену");
        jSaveScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveSceneActionPerformed(evt);
            }
        });
        jMenu2.add(jSaveScene);

        jLoadScene.setText("Загрузить сцену");
        jLoadScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoadSceneActionPerformed(evt);
            }
        });
        jMenu2.add(jLoadScene);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Вид");

        jMenuItem1.setText("Твердотельная модель");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Каркасная модель");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(workPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSelectModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSelectModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean check(){
        boolean result = true;
        
        if( !Check.holes(
                (Double)(jFirstHoleHeight.getValue()),
                (Double)(jSecondHoleHeight.getValue()),
                (Double)(jThirdHoleHeight.getValue()),
                (Double)(jSupportHeight.getValue()),
                (Double)(jBridgeLevel.getValue()),
                ((Bridge)model).bridgeHeight,
                (int)(jCountHole.getValue()) 
        ) ){
            JOptionPane.showMessageDialog(this, 
                    "Один из параметров для построения отверстий был задан неверно", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.blindingHeight((Double)jBindingHeight.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Высота крепления троса должна быть больше нуля", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if ( !Check.bridgeLenght((Double)jBridgeLength.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Длина моста должна быть больше нуля", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.bridgeLevel((Double)jBridgeLevel.getValue(), (Double)jSupportHeight.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Уровень моста должен быть меньше высоты опоры", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.bridgeWidth((Double)jBridgeWidth.getValue(), ((Bridge)model).supportWidth,
                (Double)jLowerSupportWidth.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Ширина моста не должна превышать ширину 3х опор", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.lowerSupportHeight((Double)jLowerSupportHeight.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Высота основания опоры должна быть бльше нуля", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.lowerSupportThick((Double)jLowerSupportThick.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Толщина основания опоры должна быть больше нуля", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.lowerSupportWidth((Double)jLowerSupportWidth.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Ширина основания опоры должна быть больше нуля", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.metalHeight((Double)jMetalHeight.getValue(), (Double)jBridgeLevel.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Высота металлоконструкций должна быть меньше уровня моста", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.metalWidth((Double)jMetalWidth.getValue(), (Double)jBridgeWidth.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Ширина металлоконструкций должна быть в 2 раза меньше ширины моста", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.supportHeight((Double)jSupportHeight.getValue(), (Double)jBridgeLevel.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Высота опоры должна быть больше уровня моста", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        
        if( !Check.supportThick((Double)jSupportThick.getValue(), (Double)jLowerSupportThick.getValue()) ){
            JOptionPane.showMessageDialog(this, 
                    "Толщина опоры не должна превышать тольщину основания опоры", 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
            
        return result;
    }
    
    
    private void jSaveSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveSceneActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранение файла");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(new JFileChooser());

        if (result == JFileChooser.APPROVE_OPTION ){
            try {
                serialize( fileChooser.getSelectedFile().getPath() );
                System.out.println(fileChooser.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(this, 
                              "Файл '" + fileChooser.getSelectedFile() + 
                              " сохранен");
            } catch (IOException ex) {
                Logger.getLogger(GeneralFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jSaveSceneActionPerformed

    private void jLoadSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoadSceneActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранение файла");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(new JFileChooser());

        if (result == JFileChooser.APPROVE_OPTION ){
            try {
                unserialize( fileChooser.getSelectedFile().getPath() );
                System.out.println(fileChooser.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(this, 
                              "Файл '" + fileChooser.getSelectedFile() + 
                              " открыт");
            } catch (IOException ex) {
                Logger.getLogger(GeneralFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GeneralFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLoadSceneActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        render = new RenderFill();
        workPanel.repaint();
        this.requestFocus();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        render = new RenderCabel();
        workPanel.repaint();
        this.requestFocus();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTargetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTargetButtonActionPerformed
        camera.e.x = (Double)jTargetX.getValue();
        camera.e.y = (Double)jTargetY.getValue();
        camera.e.z = (Double)jTargetZ.getValue();
        camera.setCamToDot();
        this.requestFocus();
        workPanel.repaint();
    }//GEN-LAST:event_jTargetButtonActionPerformed

    private void eCamAngleYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCamAngleYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eCamAngleYActionPerformed

    private void bChangeModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeModelActionPerformed
        if(!check()) return;
        if( !models.isEmpty() ){
            setModelParam(jSelectModel.getSelectedIndex());
            model = models.get(jSelectModel.getSelectedIndex());
            ( (Bridge)model ).createModel();
            workPanel.repaint();
            this.requestFocus();
        }
    }//GEN-LAST:event_bChangeModelActionPerformed

    private void bCreateModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateModelActionPerformed
        if( !check() ) return;
        double worldX = (Double)(jEWorldX.getValue());
        double worldY = (Double)(jEWorldY.getValue());
        double worldZ = (Double)(jEWorldZ.getValue());
        if(!check())return;
        Bridge bridge = new Bridge(worldX, worldY, worldZ, "Мост" + models.size());
        models.add(bridge);
        setModelParam(models.size() - 1);
        ( (Bridge)models.get(models.size() - 1) ).createModel();
        jSelectModel.addItem(bridge.getName());
        jSelectModel.setSelectedIndex( models.size() - 1 );

        workPanel.repaint();
        this.requestFocus();
    }//GEN-LAST:event_bCreateModelActionPerformed

    private void jChangeCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChangeCameraActionPerformed
        camera.angleX = (Double)jCameraAngleX.getValue();
        camera.angleY = (Double)jCameraAngleY.getValue();
        camera.x = (Double)jCameraX.getValue();
        camera.y = -(Double)jCameraY.getValue();
        camera.z = (Double)jCameraZ.getValue();
        workPanel.repaint();
        this.requestFocus();
    }//GEN-LAST:event_jChangeCameraActionPerformed

    private void getModelParam(int number){
        jEWorldX.setValue( models.get(number).worldX );
        jEWorldY.setValue( models.get(number).worldY );
        jEWorldZ.setValue( models.get(number).worldZ );
        jEWorldAngleX.setValue( models.get(number).angleX );
        jEWorldAngleY.setValue( models.get(number).angleY );
        jEWorldAngleZ.setValue( models.get(number).angleZ );
        jBridgeLength.setValue( ( (Bridge)models.get(number) ).bridgeLength );
        jBridgeLevel.setValue( ( (Bridge)models.get(number) ).bridgeLevel );
        jBridgeWidth.setValue( ( (Bridge)models.get(number) ).bridgeWidth );
        jSupportHeight.setValue( ( (Bridge)models.get(number) ).supportHeight );
        jSupportThick.setValue( ( (Bridge)models.get(number) ).supportThick );
        jLowerSupportHeight.setValue( ( (Bridge)models.get(number) ).lowerSupportHeight );
        jLowerSupportThick.setValue( ( (Bridge)models.get(number) ).lowerSupportThick );
        jLowerSupportWidth.setValue( ( (Bridge)models.get(number) ).lowerSupportWidth );
        jFirstHoleHeight.setValue( ( (Bridge)models.get(number) ).firstHoleHeight );
        jSecondHoleHeight.setValue( ( (Bridge)models.get(number) ).secondHoleHeight );
        jThirdHoleHeight.setValue( ( (Bridge)models.get(number) ).thirdHoleHeight );
        jBindingHeight.setValue( ( (Bridge)models.get(number) ).bindingHeight );
        jMetalHeight.setValue( ( (Bridge)models.get(number) ).metalHeight );
        jMetalWidth.setValue( ( (Bridge)models.get(number) ).metalWidth );
        jCountHole.setValue( ( (Bridge)models.get(number) ).countThirdHole );
        jCountRope.setValue( ( (Bridge)models.get(number) ).countRope );
        jScaleX.setValue(  ( (Bridge)models.get(number) ).dx );
        jScaleY.setValue(  ( (Bridge)models.get(number) ).dy );
        jScaleZ.setValue(  ( (Bridge)models.get(number) ).dz );
    }
    
    private void setModelParam(int number){
        models.get(number).worldX = (Double)( jEWorldX.getValue());
        models.get(number).worldY = (Double)( jEWorldY.getValue() );
        models.get(number).worldZ = (Double)( jEWorldZ.getValue() );
        models.get(number).angleX = (Double)( jEWorldAngleX.getValue() );
        models.get(number).angleY = (Double)( jEWorldAngleY.getValue() );
        models.get(number).angleZ = (Double)( jEWorldAngleZ.getValue() );
        ( (Bridge)models.get(number) ).bridgeLength = (Double)( jBridgeLength.getValue() );
        ( (Bridge)models.get(number) ).bridgeLevel = (Double)( jBridgeLevel.getValue() );
        ( (Bridge)models.get(number) ).bridgeWidth = (Double)( jBridgeWidth.getValue() );
        ( (Bridge)models.get(number) ).supportHeight = (Double)( jSupportHeight.getValue() );
        ( (Bridge)models.get(number) ).supportThick = (Double)( jSupportThick.getValue() );
        ( (Bridge)models.get(number) ).lowerSupportHeight = (Double)( jLowerSupportHeight.getValue() );
        ( (Bridge)models.get(number) ).lowerSupportThick = (Double)( jLowerSupportThick.getValue() );
        ( (Bridge)models.get(number) ).lowerSupportWidth = (Double)( jLowerSupportWidth.getValue() );
        ( (Bridge)models.get(number) ).firstHoleHeight = (Double)( jFirstHoleHeight.getValue() );
        ( (Bridge)models.get(number) ).secondHoleHeight = (Double)( jSecondHoleHeight.getValue() );
        ( (Bridge)models.get(number) ).thirdHoleHeight = (Double)( jThirdHoleHeight.getValue() );
        ( (Bridge)models.get(number) ).bindingHeight = (Double)( jBindingHeight.getValue() );
        ( (Bridge)models.get(number) ).metalHeight = (Double)( jMetalHeight.getValue() );
        ( (Bridge)models.get(number) ).metalWidth = (Double)( jMetalWidth.getValue() );
        ( (Bridge)models.get(number) ).countThirdHole = (int)( jCountHole.getValue() );
        ( (Bridge)models.get(number) ).countRope = (int)( jCountRope.getValue() );
        ( (Bridge)models.get(number) ).dx = (Double)( jScaleX.getValue() );
        ( (Bridge)models.get(number) ).dy = (Double)( jScaleY.getValue() );
        ( (Bridge)models.get(number) ).dz = (Double)( jScaleZ.getValue() );
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GeneralFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralFrame().setVisible(true);
            }
        });
    }
    
    private void setKeyListener(){
        this.setFocusable(true);
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
               
            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case 68:
                        model.worldX += 1;
                        break;
                    case 65:
                        model.worldX -= 1;
                        break;
                    case 87:
                        model.worldY -= 1;
                        break;
                    case 83:
                        model.worldY += 1;
                        break;
                    case 39:
                        model.angleY -= 1;
                        break;
                    case 37:
                        model.angleY += 1;
                        break;
                    case 38:
                        model.angleX += 1;
                        break;
                    case 40:
                        model.angleX -= 1;
                        break;
                    case 100:
                        camera.x -= 5;
                        break;
                    case 102:
                        camera.x += 5;
                        break;
                    case 104:
                        camera.y -= 5;
                        break;
                    case 98:
                        camera.y += 5;
                        break;
                    case 109:
                        camera.z -= 10;
                        break;
                    case 107:
                        camera.z += 10;
                        break;
                    case 105:
                        model.angleZ++;
                        break;
                    case 103:
                        model.angleZ--;
                        break;
                    case 84:
                        camera.angleX++;
                        break;
                    case 71:
                        camera.angleX--;
                        break;
                    case 72:
                        camera.angleY++;
                        break;
                    case 70:
                        camera.angleY--;
                        break;
                    default:
                        return;
                }
                
                workPanel.repaint();
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        JFrame jFrame = this;
        workPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.requestFocus();
                workPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
             
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
          
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
    }
    
    private void serialize(String name) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt( models.size() );
        for(Model m : models)
            oos.writeObject(m);
        oos.writeObject(camera);
        oos.flush();
        oos.close();
    }
    
    private void setListBox(){
        jSelectModel.removeAllItems();
        for(Model m : models)
            jSelectModel.addItem(m.getName());
    }
    
    private void unserialize(String name) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream oin = new ObjectInputStream(fis);
        models.clear();
        int count = oin.readInt();
        for(int i = 0; i < count; i++)
            models.add( (Model)oin.readObject() );
        camera = (Camera)oin.readObject();
        setListBox();
        workPanel.repaint();
    }
    
    private void setCameraEdit(){
        jCameraX.setValue(camera.x);
        jCameraY.setValue(camera.y);
        jCameraZ.setValue(camera.z);
        jCameraAngleX.setValue(camera.angleX);
        jCameraAngleY.setValue(-camera.angleY);
    }
    
    private void setModelEdit(){
        if(model != null){
            jEWorldX.setValue(model.worldX);
            jEWorldY.setValue(model.worldY);
            jEWorldZ.setValue(model.worldZ);
            jEWorldAngleX.setValue(model.angleX);
            jEWorldAngleY.setValue(model.angleY);
            jEWorldAngleZ.setValue(model.angleZ);
        }
    }
    
    private void setPaint(){
        
        Container pane = this.workPanel;
        
        pane.setLayout(new BorderLayout());
        workPanel = new JPanel(){

            @Override
            public void paintComponent(Graphics g) {
                setCameraEdit();
                setModelEdit();
                Graphics2D g2 = (Graphics2D) g;
                render.setRender(g2);
                g2.setColor(Color.lightGray);
                g2.fillRect(0, 0, getWidth(), getHeight());

                if( !models.isEmpty() ){
                    g2.drawImage( render.getImage(models, getHeight(), getWidth(), 
                            camera ), 0, 0, null);
                }
            }
        };
        pane.add(workPanel, BorderLayout.CENTER);

        this.setFocusable(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChangeModel;
    private javax.swing.JToggleButton bCreateModel;
    private javax.swing.JFormattedTextField eCamAngleX;
    private javax.swing.JFormattedTextField eCamAngleY;
    private javax.swing.JFormattedTextField eCameraX;
    private javax.swing.JFormattedTextField eCameraY;
    private javax.swing.JFormattedTextField eCameraZ;
    private javax.swing.JFormattedTextField eWorldX3;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JSpinner jBindingHeight;
    private javax.swing.JSpinner jBridgeLength;
    private javax.swing.JSpinner jBridgeLevel;
    private javax.swing.JSpinner jBridgeWidth;
    private javax.swing.JButton jButton1;
    private javax.swing.JSpinner jCameraAngleX;
    private javax.swing.JSpinner jCameraAngleY;
    private javax.swing.JSpinner jCameraX;
    private javax.swing.JSpinner jCameraY;
    private javax.swing.JSpinner jCameraZ;
    private javax.swing.JButton jChangeCamera;
    private javax.swing.JSpinner jCountHole;
    private javax.swing.JSpinner jCountRope;
    private javax.swing.JSpinner jEWorldAngleX;
    private javax.swing.JSpinner jEWorldAngleY;
    private javax.swing.JSpinner jEWorldAngleZ;
    private javax.swing.JSpinner jEWorldX;
    private javax.swing.JSpinner jEWorldY;
    private javax.swing.JSpinner jEWorldZ;
    private javax.swing.JSpinner jFirstHoleHeight;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jLoadScene;
    private javax.swing.JSpinner jLowerSupportHeight;
    private javax.swing.JSpinner jLowerSupportThick;
    private javax.swing.JSpinner jLowerSupportWidth;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSpinner jMetalHeight;
    private javax.swing.JSpinner jMetalWidth;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem jSaveScene;
    private javax.swing.JSpinner jScaleX;
    private javax.swing.JSpinner jScaleY;
    private javax.swing.JSpinner jScaleZ;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSecondHoleHeight;
    private javax.swing.JComboBox<String> jSelectModel;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane10;
    private javax.swing.JSplitPane jSplitPane11;
    private javax.swing.JSplitPane jSplitPane12;
    private javax.swing.JSplitPane jSplitPane13;
    private javax.swing.JSplitPane jSplitPane14;
    private javax.swing.JSplitPane jSplitPane15;
    private javax.swing.JSplitPane jSplitPane16;
    private javax.swing.JSplitPane jSplitPane17;
    private javax.swing.JSplitPane jSplitPane18;
    private javax.swing.JSplitPane jSplitPane19;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JSplitPane jSplitPane7;
    private javax.swing.JSplitPane jSplitPane8;
    private javax.swing.JSplitPane jSplitPane9;
    private javax.swing.JSpinner jSupportHeight;
    private javax.swing.JSpinner jSupportThick;
    private javax.swing.JButton jTargetButton;
    private javax.swing.JSpinner jTargetX;
    private javax.swing.JSpinner jTargetY;
    private javax.swing.JSpinner jTargetZ;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JSpinner jThirdHoleHeight;
    private javax.swing.JPanel workPanel;
    // End of variables declaration//GEN-END:variables
}
