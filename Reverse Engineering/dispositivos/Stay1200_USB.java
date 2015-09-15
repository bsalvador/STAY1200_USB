/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloPS;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Stay1200_USB
/*     */   extends PS800
/*     */ {
/*     */   protected int estadoReles;
/*     */   
/*     */   public Stay1200_USB(ProtocoloPS protocol)
/*     */   {
/*  29 */     super(protocol);
/*  30 */     instanciasGlobais();
/*  31 */     protocol.setModeloUPS(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void instanciasGlobais()
/*     */   {
/*  39 */     this.TENSAO_ENTRADA_F1 = 1.64F;
/*  40 */     this.TENSAO_ENTRADA_F2 = 9.34F;
/*  41 */     this.TENSAO_BATERIA_F1 = 0.1551D;
/*  42 */     this.TENSAO_BATERIA_F2 = 0.2525D;
/*     */     
/*  44 */     this.TENSAO_MIN_BATERIA = 20.0F;
/*  45 */     this.TENSAO_MAX_BATERIA = 29.5F;
/*  46 */     this.TENSAO_FLUT_BATERIA = 27.0F;
/*     */     
/*  48 */     this.CORRENTE_SAIDA_F1_MR = 0.12970000389100012D;
/*  49 */     this.CORRENTE_SAIDA_F2_MR = 0.5387060281204546D;
/*     */     
/*  51 */     this.CORRENTE_SAIDA_F1_MI = 0.1372D;
/*  52 */     this.CORRENTE_SAIDA_F2_MI = 0.3456D;
/*     */     
/*  54 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F1 = new double[] { 0.079D, 0.089D, 0.0972D, 0.0D, 0.0805D, 0.0883D, 0.0D, 0.0981D };
/*  55 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F1 = new double[] { 0.0763D, 0.081D, 0.0919D, 0.0D, 0.0741D, 0.0828D, 0.0D, 0.0938D };
/*  56 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F1 = new double[] { 0.0744D, 0.0808D, 0.0885D, 0.0D, 0.0732D, 0.084D, 0.0D, 0.0955D };
/*     */     
/*  58 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F2 = new double[] { 49.107D, 45.449D, 48.092D, 0.0D, 43.633D, 47.585D, 0.0D, 48.831D };
/*  59 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F2 = new double[] { 81.732D, 94.459D, 86.686D, 0.0D, 84.657D, 84.999D, 0.0D, 78.097D };
/*  60 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F2 = new double[] { 122.06D, 122.9D, 125.75D, 0.0D, 120.39D, 108.52D, 0.0D, 92.239D };
/*     */     
/*  62 */     this.POTENCIAUTIL_CURVA1_F1 = new double[] { 0.08040007075206226D, 0.0894D, 0.0999D, 0.0D, 0.0813D, 0.0905D, 0.0D, 0.1005D };
/*  63 */     this.POTENCIAUTIL_CURVA2_F1 = new double[] { 0.08630063689870031D, 0.0946D, 0.1068D, 0.0D, 0.086D, 0.0967D, 0.0D, 0.1088D };
/*  64 */     this.POTENCIAUTIL_CURVA3_F1 = new double[] { 0.0896001146881468D, 0.0991D, 0.1116D, 0.0D, 0.0967D, 0.1068D, 0.0D, 0.1169D };
/*     */     
/*  66 */     this.POTENCIAUTIL_CURVA1_F2 = new double[] { 45.292D, 41.928D, 41.727D, 0.0D, 40.269D, 41.81D, 0.0D, 43.458D };
/*  67 */     this.POTENCIAUTIL_CURVA2_F2 = new double[] { 8.3927D, 9.2393D, 8.2852D, 0.0D, 8.301D, 6.7636D, 0.0D, 8.2842D };
/*  68 */     this.POTENCIAUTIL_CURVA3_F2 = new double[] { -31.115D, -33.777D, -33.826D, 0.0D, -59.513D, -57.729D, 0.0D, -41.333D };
/*     */     
/*     */ 
/*     */ 
/*  72 */     this.TENSAO_SAIDA_F1_MR = new double[] { 1.1549D, 1.0925D, 0.0D, 0.0D, 1.0929D, 1.0885D, 0.0D, 0.8654262224145391D };
/*  73 */     this.TENSAO_SAIDA_F2_MR = new double[] { -6.9157D, 11.026D, 10.43D, 0.0D, -0.6109D, 12.18D, 0.0D, 13.677D };
/*     */     
/*  75 */     this.TENSAO_SAIDA_F2_MI = new double[] { 5.59D, 9.47D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  76 */     this.TENSAO_SAIDA_F1_MI = new double[] { 7.9D, 9.1D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */     
/*  78 */     this.autonomia100 = new int[] { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/*  87 */     if (this.potenciaReal > 655.0F)
/*     */     {
/*  89 */       this.sobrecarga = true;
/*  90 */       this.cargaElevada = true;
/*     */     }
/*     */     else
/*     */     {
/*  94 */       this.sobrecarga = false;
/*  95 */       this.cargaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 110 */     double potVA1 = 5.968D * this.potenciaAparente - 284.36D;
/* 111 */     double potVA2 = 7.149D * this.potenciaAparente - 567.18D;
/* 112 */     double potLin = 0.1664D * potenciaReal + 49.182D;
/* 113 */     double potRe = 0.1519D * potenciaReal + 32.644D;
/*     */     
/*     */ 
/*     */ 
/* 117 */     if (Math.abs(potVA1 - potenciaReal) < Math.abs(potVA2 - potenciaReal)) {
/* 118 */       this.potenciaReal = ((float)potLin);
/*     */     } else {
/* 120 */       this.potenciaReal = ((float)potRe);
/*     */     }
/* 122 */     if (this.correnteSaida < 0.7D) {
/* 123 */       this.potenciaReal = this.potenciaAparente;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 130 */     if (this.potenciaAparente < this.potenciaReal)
/*     */     {
/* 132 */       float f = this.potenciaAparente;
/* 133 */       this.potenciaAparente = this.potenciaReal;
/* 134 */       this.potenciaReal = f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int autonomiaBateria)
/*     */   {
/* 147 */     if (this.potenciaReal < 120.0F)
/*     */     {
/* 149 */       this.autonomiaBateria = Math.round((float)(0.0982D * Math.pow(autonomiaBateria, 2.0D) - 27.131D * autonomiaBateria + 1875.18D));
/* 150 */       if (this.autonomiaBateria > 60) {
/* 151 */         this.autonomiaBateria = 60;
/*     */       }
/*     */       
/*     */     }
/* 155 */     else if (this.potenciaReal < 230.0F)
/*     */     {
/* 157 */       this.autonomiaBateria = Math.round((float)(0.0663D * Math.pow(autonomiaBateria, 2.0D) - 18.252D * autonomiaBateria + 1257.2D));
/* 158 */       if (this.autonomiaBateria > 30) {
/* 159 */         this.autonomiaBateria = 30;
/*     */       }
/*     */       
/*     */     }
/* 163 */     else if (this.potenciaReal < 330.0F)
/*     */     {
/* 165 */       this.autonomiaBateria = Math.round((float)(0.0225D * Math.pow(autonomiaBateria, 2.0D) - 5.9113D * autonomiaBateria + 389.08D));
/* 166 */       if (this.autonomiaBateria > 13) {
/* 167 */         this.autonomiaBateria = 13;
/*     */       }
/*     */       
/*     */     }
/* 171 */     else if (this.potenciaReal < 430.0F)
/*     */     {
/* 173 */       this.autonomiaBateria = Math.round((float)(9.0E-4D * Math.pow(autonomiaBateria, 2.0D) + 3.3172D * autonomiaBateria - 272.34D));
/* 174 */       if (this.autonomiaBateria > 10) {
/* 175 */         this.autonomiaBateria = 10;
/*     */       }
/*     */       
/*     */     }
/* 179 */     else if (this.potenciaReal < 530.0F)
/*     */     {
/* 181 */       this.autonomiaBateria = Math.round((float)(0.5711D * Math.pow(this.tensaoBateria, 2.0D) - 23.484D * this.tensaoBateria + 241.81D));
/* 182 */       if (this.autonomiaBateria > 8) {
/* 183 */         this.autonomiaBateria = 8;
/*     */       }
/*     */       
/*     */     }
/* 187 */     else if (this.potenciaReal < 630.0F)
/*     */     {
/* 189 */       this.autonomiaBateria = Math.round((float)(0.0091D * Math.pow(autonomiaBateria, 2.0D) - 2.3133D * autonomiaBateria + 147.62D));
/* 190 */       if (this.autonomiaBateria > 8) {
/* 191 */         this.autonomiaBateria = 8;
/*     */       }
/*     */     }
/* 194 */     if (this.expansorBateria != 0) {
/* 195 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 205 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 218 */     if (isTensaoSaida220()) {
/* 219 */       this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/*     */     } else {
/* 221 */       this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2 - 3.0F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 233 */     if (this.modoRede)
/*     */     {
/* 235 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */ 
/*     */     }
/* 239 */     else if (this.saidaLigada) {
/* 240 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 242 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */     
/* 245 */     if (correnteSaida == 0) {
/* 246 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 254 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 265 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 268 */     int mesAtual = calendar.get(2) + 1;
/* 269 */     int anoAtual = calendar.get(1);
/*     */     
/* 271 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/*     */     
/* 273 */     this.protocoloUPS.addEvento(evt);
/*     */     
/* 275 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 283 */     if (this.modoBateria) {
/* 284 */       this.bateriaBaixa = (this.tensaoBateria < 19.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\Stay1200_USB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */