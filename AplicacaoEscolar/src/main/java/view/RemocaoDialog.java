/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.AlunoControle;
import dao.RelatorioDao;
import javassist.tools.rmi.RemoteException;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class RemocaoDialog extends javax.swing.JDialog {

    private final AlunoControle alunoControle;
    private final RelatorioDao relatorioDao;
    /**
     * Creates new form RemocaoDialog
     */
    public RemocaoDialog() {
        alunoControle = new AlunoControle();
        relatorioDao = new RelatorioDao();
        initComponents();
    }

    public AlunoControle getAlunoControle() {
        return alunoControle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        lblMat = new javax.swing.JLabel();
        btRemover = new javax.swing.JButton();
        txtMatId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remoção de Aluno");
        setModal(true);

        lblMat.setText("Matrícula do Aluno:");

        btRemover.setText("Remover");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${alunoControle.alunoDigitado.id}"), txtMatId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btRemover)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMat)
                    .addComponent(btRemover)
                    .addComponent(txtMatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, 
                "Deseja realmente remover este Aluno? \n\n"
                        + relatorioDao.pesquisarAluno(Long.parseLong(txtMatId.getText())).dadosAluno(),
                "Remover Aluno",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            
            try {
                alunoControle.remover();
                JOptionPane.showMessageDialog(this, 
                    "Aluno removido com sucesso",
                    "Remover Aluno",
                    JOptionPane.INFORMATION_MESSAGE);              
            } catch(RemoteException e ){
                JOptionPane.showMessageDialog(this,
                    "Erro "+e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }        
        }
        this.setVisible(false);
    }//GEN-LAST:event_btRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRemover;
    private javax.swing.JLabel lblMat;
    private javax.swing.JTextField txtMatId;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}