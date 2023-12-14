import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class Compdecomp implements ActionListener {
    JButton compressButton;
    JButton decompressButton;
    Compdecomp(){
        //creating the buttons
        compressButton=new JButton("Select file to compress");
        compressButton.setBounds(20,100,200,30);
        decompressButton=new JButton("Select file to decompress");
        decompressButton.setBounds(20,200,200,30);

        //adding the action listeners to buttons
        compressButton.addActionListener(this);
        decompressButton.addActionListener(this);

        //setting the frame for displaying
        JFrame frame=new JFrame();
        frame.setLayout(null);
        frame.add(compressButton);
        frame.add(decompressButton);
        frame.setSize(1000,500);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==compressButton)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                String fileDirectory=file.getParent();
                try{
                    FileInputStream fis=new FileInputStream(file);
                    FileOutputStream fos=new FileOutputStream(fileDirectory+"/CompressedFile.gz");
                    GZIPOutputStream gzip=new GZIPOutputStream(fos);
                    byte[] buffer=new byte[1024];
                    int len;
                    while((len=fis.read(buffer))!=-1)
                    {
                        gzip.write(buffer,0,len);
                    }
                    fis.close();
                    fos.close();
                    gzip.close();

                }catch(IOException ee){
                    ee.printStackTrace();
                }
            }
        }
        if(e.getSource()==decompressButton)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                String fileDirectory=file.getParent();
                try{
                    FileInputStream fis=new FileInputStream(file);
                    GZIPInputStream gzip=new GZIPInputStream(fis);
                    FileOutputStream fos=new FileOutputStream(fileDirectory+"/DecompressedFile");
                    byte[] buffer=new byte[1024];
                    int len;
                    while((len=gzip.read(buffer))!=-1)
                    {
                        fos.write(buffer,0,len);
                    }
                    fis.close();
                    fos.close();
                    gzip.close();
                }catch(IOException ee){
                    ee.printStackTrace();
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Compdecomp compDecomp=new Compdecomp();
    }
}
