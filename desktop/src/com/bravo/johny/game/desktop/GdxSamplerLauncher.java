package com.bravo.johny.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.bravo.johny.game.common.SampleFactory;
import com.bravo.johny.game.common.SampleInfos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 640;
    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;
    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList sampleList;
    private JPanel controlPanel;

    public GdxSamplerLauncher() throws HeadlessException {
        init();
    }

    private void init() {
        createControlPanel();
        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (lwjglAWTCanvas != null) {
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //tells window(jframe) to resize and layout our components
        pack();
        setVisible(true);

    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        sampleList = new JList(SampleInfos.getSampleNames().toArray());
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2)
                    launchSelectedSample();
            }
        });

        // Constraints for the scroll pane
        gridBagConstraints.gridx = 0;   // column
        gridBagConstraints.gridy = 0;   // row
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;  // fill vertically
        gridBagConstraints.weighty = 1.0;   // weight used when fill empty space

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, gridBagConstraints);

        // constraints for the button
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0;

        JButton launchButton = new JButton("Launch");
        launchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchSelectedSample();
            }
        });
        controlPanel.add(launchButton, gridBagConstraints);
    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();

        if(sampleName == null || sampleName.isEmpty()) {
            System.out.println("Can not launch .. Sample name is empty !!");
            return;
        }
        launchSample(sampleName);
    }

    private void launchSample(String name) {
        ApplicationListener sample = null;

        System.out.println("Launching sample : Name = "+name);

        Container container = getContentPane();

        if(lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        sample = SampleFactory.newSample(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();

    }

    public static void main(String[] args) {
        //used to run our jframe properly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }
}