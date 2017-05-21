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
        
       
        camera.e = new Vertex(100, -50, 500);
        camera.setCamToDot();
        
        System.out.println(jSupportHeight.getValue() + " " + (jSupportHeight.getValue().getClass()));
        
        
    }
    
    private void setSelectListener(){
        jSelectModel.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && jSelectModel.getSelectedIndex() > -1 && !models.isEmpty() ){
                    model = models.get( jSelectModel.getSelectedIndex() );
                    getModelParam( jSelectModel.getSelectedIndex() );
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
        workPanel = new javax.swing.JPanel();
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
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
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
        jEWorldX = new javax.swing.JSpinner();
        jEWorldY = new javax.swing.JSpinner();
        jEWorldZ = new javax.swing.JSpinner();
        jEWorldAngleX = new javax.swing.JSpinner();
        jEWorldAngleY = new javax.swing.JSpinner();
        jEWorldAngleZ = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        eCameraX = new javax.swing.JFormattedTextField();
        eCameraY = new javax.swing.JFormattedTextField();
        eCameraZ = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        eCamAngleX = new javax.swing.JFormattedTextField();
        eCamAngleY = new javax.swing.JFormattedTextField();
        eCamAngleZ = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSelectModel = new javax.swing.JComboBox<>();
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
            .addGap(0, 683, Short.MAX_VALUE)
        );
        workPanelLayout.setVerticalGroup(
            workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

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

        jLabel18.setText("X");

        jLabel19.setText("Y");

        jLabel20.setText("Z");

        jLabel29.setText("angleX");

        jLabel31.setText("angleY");

        jLabel32.setText("angleZ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSecondHoleHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jThirdHoleHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBindingHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFirstHoleHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSupportHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSupportThick, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLowerSupportThick, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(113, 113, 113)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLowerSupportHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLowerSupportWidth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(bChangeModel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBridgeWidth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBridgeLength, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMetalHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBridgeLevel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCountRope, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCountHole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bCreateModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jMetalWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jEWorldX, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jEWorldAngleX, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jEWorldAngleY)
                            .addComponent(jEWorldY, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jEWorldZ, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEWorldAngleZ, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFirstHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSecondHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jThirdHoleHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jBindingHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLowerSupportThick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLowerSupportHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLowerSupportWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMetalWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jMetalHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jBridgeLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jBridgeWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jBridgeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jCountRope, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jCountHole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jEWorldX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEWorldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEWorldZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jEWorldAngleX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEWorldAngleY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEWorldAngleZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCreateModel)
                    .addComponent(bChangeModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel21.setText("Камера");

        jLabel22.setText("X");

        jLabel23.setText("Y");

        jLabel24.setText("Z");

        jLabel26.setText("angleX");

        jLabel27.setText("angleY");

        jLabel28.setText("angleZ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel25)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel22))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eCamAngleX, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(eCameraX))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eCamAngleY, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(eCameraY))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eCamAngleZ, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(eCameraZ))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(159, 159, 159))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eCameraX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eCameraY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eCameraZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eCamAngleZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eCamAngleY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(eCamAngleX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(33, 33, 33))
        );

        jScrollPane2.setViewportView(jPanel2);

        jSelectModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jMenuItem1.setText("Закраска");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Проволка");
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
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jSelectModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSelectModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        render = new RenderCabel();
        workPanel.repaint();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void bChangeModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeModelActionPerformed
        if(!check()) return;
        if( !models.isEmpty() ){
            setModelParam(jSelectModel.getSelectedIndex());
            model = models.get(jSelectModel.getSelectedIndex());
            ( (Bridge)model ).createModel();
            workPanel.repaint();
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
        workPanel.requestFocus(true);
        workPanel.requestFocus();
    }//GEN-LAST:event_bCreateModelActionPerformed

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
                      //  model.dx -= 0.1;
                      //  model.dy -= 0.1;
                      //  model.dz -= 0.1;
                        camera.z -= 10;
                        break;
                    case 107:
                       // model.dx += 0.1;
                       // model.dy += 0.1;
                      //  model.dz += 0.1;
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
        eCameraX.setValue(camera.x);
        eCameraY.setValue(camera.y);
        eCameraZ.setValue(camera.z);
        eCamAngleX.setValue(camera.angleX);
        eCamAngleY.setValue(camera.angleY);
        eCamAngleZ.setValue(camera.angleZ);
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
    private javax.swing.JFormattedTextField eCamAngleZ;
    private javax.swing.JFormattedTextField eCameraX;
    private javax.swing.JFormattedTextField eCameraY;
    private javax.swing.JFormattedTextField eCameraZ;
    private javax.swing.JFormattedTextField eWorldX3;
    private javax.swing.JSpinner jBindingHeight;
    private javax.swing.JSpinner jBridgeLength;
    private javax.swing.JSpinner jBridgeLevel;
    private javax.swing.JSpinner jBridgeWidth;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSecondHoleHeight;
    private javax.swing.JComboBox<String> jSelectModel;
    private javax.swing.JSpinner jSupportHeight;
    private javax.swing.JSpinner jSupportThick;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JSpinner jThirdHoleHeight;
    private javax.swing.JPanel workPanel;
    // End of variables declaration//GEN-END:variables
}
