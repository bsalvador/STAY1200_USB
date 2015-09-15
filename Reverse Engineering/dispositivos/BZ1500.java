/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloPS;
/*     */ import java.io.PrintStream;
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
/*     */ 
/*     */ 
/*     */ public class BZ1500
/*     */   extends PS800
/*     */ {
/*     */   public BZ1500(ProtocoloPS protocol)
/*     */   {
/*  30 */     super(protocol);
/*  31 */     instanciasGlobais();
/*  32 */     protocol.setModeloUPS(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void instanciasGlobais()
/*     */   {
/*  40 */     this.TENSAO_ENTRADA_F1 = 1.8F;
/*  41 */     this.TENSAO_ENTRADA_F2 = 2.224F;
/*  42 */     this.TENSAO_BATERIA_F1 = 0.1513D;
/*  43 */     this.TENSAO_BATERIA_F2 = 0.7153D;
/*     */     
/*  45 */     this.TENSAO_MIN_BATERIA = 20.0F;
/*  46 */     this.TENSAO_MAX_BATERIA = 29.5F;
/*  47 */     this.TENSAO_FLUT_BATERIA = 27.0F;
/*     */     
/*  49 */     this.CORRENTE_SAIDA_F1_MR = 0.1264D;
/*  50 */     this.CORRENTE_SAIDA_F2_MR = 0.522D;
/*     */     
/*  52 */     this.CORRENTE_SAIDA_F1_MI = 0.1303D;
/*  53 */     this.CORRENTE_SAIDA_F2_MI = 0.468D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  73 */     this.TENSAO_SAIDA_F1_MR = new double[] { 0.9266D, 0.9266D, 0.9266D, 0.9266D, 0.9266D, 0.9266D, 0.9266D, 0.9266D };
/*  74 */     this.TENSAO_SAIDA_F2_MR = new double[] { 5.0694D, 5.0694D, 5.0694D, 5.0694D, 5.0694D, 5.0694D, 5.0694D, 5.0694D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */     this.TENSAO_SAIDA_F2_MI = new double[] { 5.59D, 9.47D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  81 */     this.TENSAO_SAIDA_F1_MI = new double[] { 5.4D, 6.5D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  82 */     this.autonomia100 = new int[] { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/*  91 */     if (this.potenciaReal > 820.0F)
/*     */     {
/*  93 */       this.sobrecarga = true;
/*  94 */       this.cargaElevada = true;
/*     */     }
/*     */     else
/*     */     {
/*  98 */       this.sobrecarga = false;
/*  99 */       this.cargaElevada = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 132 */     this.potenciaReal = ((int)(0.1127D * potenciaReal + 50.031D));
/*     */     
/* 134 */     if (this.correnteSaida < 0.7D)
/*     */     {
/* 136 */       this.potenciaReal = this.potenciaAparente;
/*     */     }
/* 138 */     if (this.potenciaAparente < this.potenciaReal)
/*     */     {
/* 140 */       float f = this.potenciaAparente;
/* 141 */       this.potenciaAparente = this.potenciaReal;
/* 142 */       this.potenciaReal = f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int autonomiaBateria)
/*     */   {
/* 153 */     if (this.potenciaReal < 120.0F)
/*     */     {
/* 155 */       this.autonomiaBateria = Math.round((float)(0.0982D * Math.pow(autonomiaBateria, 2.0D) - 27.131D * autonomiaBateria + 1875.18D));
/* 156 */       if (this.autonomiaBateria > 60) {
/* 157 */         this.autonomiaBateria = 60;
/*     */       }
/*     */       
/*     */     }
/* 161 */     else if (this.potenciaReal < 230.0F)
/*     */     {
/* 163 */       this.autonomiaBateria = Math.round((float)(0.0663D * Math.pow(autonomiaBateria, 2.0D) - 18.252D * autonomiaBateria + 1257.2D));
/* 164 */       if (this.autonomiaBateria > 30) {
/* 165 */         this.autonomiaBateria = 30;
/*     */       }
/*     */       
/*     */     }
/* 169 */     else if (this.potenciaReal < 330.0F)
/*     */     {
/* 171 */       this.autonomiaBateria = Math.round((float)(0.0225D * Math.pow(autonomiaBateria, 2.0D) - 5.9113D * autonomiaBateria + 389.08D));
/* 172 */       if (this.autonomiaBateria > 13) {
/* 173 */         this.autonomiaBateria = 13;
/*     */       }
/*     */       
/*     */     }
/* 177 */     else if (this.potenciaReal < 430.0F)
/*     */     {
/* 179 */       this.autonomiaBateria = Math.round((float)(9.0E-4D * Math.pow(autonomiaBateria, 2.0D) + 3.3172D * autonomiaBateria - 272.34D));
/* 180 */       if (this.autonomiaBateria > 10) {
/* 181 */         this.autonomiaBateria = 10;
/*     */       }
/*     */       
/*     */     }
/* 185 */     else if (this.potenciaReal < 530.0F)
/*     */     {
/* 187 */       this.autonomiaBateria = Math.round((float)(0.5711D * Math.pow(this.tensaoBateria, 2.0D) - 23.484D * this.tensaoBateria + 241.81D));
/* 188 */       if (this.autonomiaBateria > 8) {
/* 189 */         this.autonomiaBateria = 8;
/*     */       }
/*     */       
/*     */     }
/* 193 */     else if (this.potenciaReal < 630.0F)
/*     */     {
/* 195 */       this.autonomiaBateria = Math.round((float)(0.0091D * Math.pow(autonomiaBateria, 2.0D) - 2.3133D * autonomiaBateria + 147.62D));
/* 196 */       if (this.autonomiaBateria > 8) {
/* 197 */         this.autonomiaBateria = 8;
/*     */       }
/*     */     }
/* 200 */     else if (this.potenciaReal < 730.0F)
/*     */     {
/* 202 */       this.autonomiaBateria = Math.round((float)(9.0E-4D * Math.pow(autonomiaBateria, 2.0D) - 1.422D * autonomiaBateria + 136.22D));
/* 203 */       if (this.autonomiaBateria > 6) {
/* 204 */         this.autonomiaBateria = 6;
/*     */       }
/*     */     }
/* 207 */     if (this.expansorBateria != 0) {
/* 208 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 218 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
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
/* 231 */     this.tensaoEntrada = ((int)(-1.0E-4F * tensaoEntrada * tensaoEntrada + 1.8234F * tensaoEntrada + 1.2181F));
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
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 247 */     if (this.modoRede)
/*     */     {
/* 249 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */ 
/*     */     }
/* 253 */     else if (this.saidaLigada) {
/* 254 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 256 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */     
/* 259 */     if (correnteSaida == 0) {
/* 260 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 268 */     return true;
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
/* 279 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 282 */     int mesAtual = calendar.get(2) + 1;
/* 283 */     int anoAtual = calendar.get(1);
/*     */     
/* 285 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/*     */     
/* 287 */     this.protocoloUPS.addEvento(evt);
/*     */     
/* 289 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 297 */     if (this.modoBateria) {
/* 298 */       this.bateriaBaixa = (this.tensaoBateria < 19.0F);
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
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 311 */     if (!this.saidaLigada)
/*     */     {
/* 313 */       this.tensaoSaida = 0.0F;
/*     */     }
/* 315 */     else if (this.modoRede)
/*     */     {
/* 317 */       this.tensaoSaida = ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida - this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/*     */     }
/*     */     else
/*     */     {
/* 321 */       double a = tensaoSaida * 2;
/* 322 */       a /= 128.0D;
/*     */       
/* 324 */       this.tensaoSaida = ((float)(this.tensaoBateria * Math.sqrt(a) * this.TENSAO_SAIDA_F1_MI[this.estadoReles] - this.correnteSaida * this.TENSAO_SAIDA_F2_MI[this.estadoReles]));
/*     */       
/* 326 */       System.out.println(this.TENSAO_SAIDA_F1_MI[this.estadoReles]);
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
/*     */ 
/*     */   public void setConfiguracaoReles(int conf)
/*     */   {
/* 357 */     this.estadoReles = ((conf & 0x28) >> 3);
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\BZ1500.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */