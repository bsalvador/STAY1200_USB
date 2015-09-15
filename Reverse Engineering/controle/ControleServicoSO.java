/*    */ package br.com.schneider.sgm.controle;
/*    */ 
/*    */ import br.com.schneider.sgm.persistencia.Persistencia;
/*    */ import br.com.schneider.sgm.rmi.ServidorRMI;
/*    */ import java.rmi.RemoteException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControleServicoSO
/*    */ {
/*    */   private Controle controleServicodeSO;
/*    */   public static final boolean TESTE_DESENVOLVIMENTO = false;
/*    */   
/*    */   public ControleServicoSO(String caminho)
/*    */   {
/* 27 */     this.controleServicodeSO = new Controle(caminho);
/*    */     
/* 29 */     this.controleServicodeSO.setServicoSO(true);
/*    */     
/* 31 */     this.controleServicodeSO.initControle();
/*    */     
/* 33 */     if (this.controleServicodeSO.persistencia.isPrimeiraExec()) {
/* 34 */       this.controleServicodeSO.primeiraExecucao = true;
/*    */     } else {
/* 36 */       this.controleServicodeSO.primeiraExecucao = false;
/* 37 */       this.controleServicodeSO.configuraSGM();
/*    */     }
/*    */     
/* 40 */     String sistemaOperacional = System.getProperty("os.name").toLowerCase();
/* 41 */     boolean isWindows = sistemaOperacional.indexOf("windows") >= 0;
/*    */     
/* 43 */     if (!isWindows)
/*    */     {
/*    */       try
/*    */       {
/* 47 */         new ServidorRMI().start(this.controleServicodeSO, Boolean.valueOf(false));
/*    */       }
/*    */       catch (RemoteException e) {
/* 50 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 68 */     new ControleServicoSO(args[0]);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void encerraAplicativo(String[] args)
/*    */   {
/* 80 */     System.exit(0);
/*    */   }
/*    */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\controle\ControleServicoSO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */