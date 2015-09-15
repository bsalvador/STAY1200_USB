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
/*     */ public class PS2200
/*     */   extends PS800
/*     */ {
/*     */   protected float TENSAO_ENTRADA_F1_115;
/*     */   protected float TENSAO_ENTRADA_F2_115;
/*     */   
/*     */   public PS2200(ProtocoloPS protocol)
/*     */   {
/*  30 */     super(protocol);
/*  31 */     instanciasGlobais();
/*  32 */     protocol.setModeloUPS(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void instanciasGlobais()
/*     */   {
/*  39 */     this.TENSAO_ENTRADA_F1 = 1.61F;
/*  40 */     this.TENSAO_ENTRADA_F2 = -1.15F;
/*     */     
/*  42 */     this.TENSAO_ENTRADA_F1_115 = 1.58F;
/*  43 */     this.TENSAO_ENTRADA_F2_115 = 2.86F;
/*     */     
/*  45 */     this.TENSAO_BATERIA_F1 = 0.16D;
/*  46 */     this.TENSAO_BATERIA_F2 = -0.76D;
/*     */     
/*  48 */     this.TENSAO_MIN_BATERIA = 20.0F;
/*  49 */     this.TENSAO_MAX_BATERIA = 29.5F;
/*  50 */     this.TENSAO_FLUT_BATERIA = 27.0F;
/*     */     
/*  52 */     this.CORRENTE_SAIDA_F1_MR = 0.15000000596046448D;
/*  53 */     this.CORRENTE_SAIDA_F2_MR = 0.7900000214576721D;
/*     */     
/*  55 */     this.CORRENTE_SAIDA_F1_MI = 0.15000000596046448D;
/*  56 */     this.CORRENTE_SAIDA_F2_MI = 0.699999988079071D;
/*     */     
/*  58 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F1 = new double[] { 0.24D, 0.26D, 0.0D, 0.0D, 
/*  59 */       0.24D, 0.26D, 0.0D, 0.28D };
/*  60 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F1 = new double[] { 0.0763D, 0.081D, 0.0919D, 
/*  61 */       0.0D, 0.0741D, 0.0828D, 0.0D, 0.0938D };
/*  62 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F1 = new double[] { 0.0744D, 0.0808D, 
/*  63 */       0.0885D, 0.0D, 0.0732D, 0.084D, 0.0D, 0.0955D };
/*     */     
/*  65 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F2 = new double[] { 83.15D, 81.23D, 0.0D, 0.0D, 
/*  66 */       83.49D, 79.05D, 0.0D, 85.67D };
/*  67 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F2 = new double[] { 81.732D, 94.459D, 
/*  68 */       86.686D, 0.0D, 84.657D, 84.999D, 0.0D, 78.097D };
/*  69 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F2 = new double[] { 122.06D, 122.9D, 125.75D, 
/*  70 */       0.0D, 120.39D, 108.52D, 0.0D, 92.239D };
/*     */     
/*  72 */     this.POTENCIAUTIL_CURVA1_F1 = new double[] { 0.08040007075206226D, 0.0894D, 0.0999D, 0.0D, 
/*  73 */       0.0813D, 0.0905D, 0.0D, 0.1005D };
/*  74 */     this.POTENCIAUTIL_CURVA2_F1 = new double[] { 0.08630063689870031D, 0.0946D, 0.1068D, 0.0D, 
/*  75 */       0.086D, 0.0967D, 0.0D, 0.1088D };
/*  76 */     this.POTENCIAUTIL_CURVA3_F1 = new double[] { 0.0896001146881468D, 0.0991D, 0.1116D, 0.0D, 
/*  77 */       0.0967D, 0.1068D, 0.0D, 0.1169D };
/*     */     
/*  79 */     this.POTENCIAUTIL_CURVA1_F2 = new double[] { 45.292D, 41.928D, 41.727D, 0.0D, 
/*  80 */       40.269D, 41.81D, 0.0D, 43.458D };
/*  81 */     this.POTENCIAUTIL_CURVA2_F2 = new double[] { 8.3927D, 9.2393D, 8.2852D, 0.0D, 
/*  82 */       8.301D, 6.7636D, 0.0D, 8.2842D };
/*  83 */     this.POTENCIAUTIL_CURVA3_F2 = new double[] { -31.115D, -33.777D, -33.826D, 0.0D, 
/*  84 */       -59.513D, -57.729D, 0.0D, -41.333D };
/*     */     
/*  86 */     this.TENSAO_SAIDA_F1_MR = new double[] { 1.61D, 1.71D, 0.0D, 0.0D, 1.64D, 1.64D, 0.0D, 
/*  87 */       1.72D };
/*  88 */     this.TENSAO_SAIDA_F2_MR = new double[] { -1.03D, 2.45D, 0.0D, 0.0D, -3.06D, 6.94D, 0.0D, 
/*  89 */       11.63D };
/*     */     
/*  91 */     this.TENSAO_SAIDA_F1_MI = new double[] { 7.9D, 9.1D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  92 */     this.TENSAO_SAIDA_F2_MI = new double[] { 5.59D, 9.47D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */     
/*  94 */     this.autonomia100 = new int[] { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 
/*  95 */       13, 11, 9, 7, 4, 2, 1 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/* 103 */     if (this.potenciaReal > 1200.0F) {
/* 104 */       this.sobrecarga = true;
/* 105 */       this.cargaElevada = true;
/*     */     }
/*     */     else {
/* 108 */       this.sobrecarga = false;
/* 109 */       this.cargaElevada = false;
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
/* 124 */     double potRe = this.POTENCIAUTIL_DETECCAO_CURVA1_F1[this.estadoReles] * potenciaReal + 
/* 125 */       this.POTENCIAUTIL_DETECCAO_CURVA1_F2[this.estadoReles];
/*     */     
/*     */ 
/*     */ 
/* 129 */     if ((!this.saidaLigada) || (this.potenciaAparente == 0.0F)) {
/* 130 */       potRe = 0.0D;
/*     */     }
/* 132 */     this.potenciaReal = ((float)potRe);
/*     */     
/* 134 */     if (this.potenciaReal > this.potenciaAparente) {
/* 135 */       this.potenciaAparente = this.potenciaReal;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int tensaoBateria)
/*     */   {
/* 145 */     float autonomiaBateria = (float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2);
/*     */     
/* 147 */     if (!this.saidaLigada)
/*     */     {
/*     */ 
/* 150 */       this.autonomiaBateria = 0;
/* 151 */       return;
/*     */     }
/*     */     
/* 154 */     if (this.potenciaReal < 103.0F) {
/* 155 */       this.autonomiaBateria = Math.round(
/*     */       
/* 157 */         (float)(4.0561D * Math.pow(autonomiaBateria, 2.0D) - 175.36D * autonomiaBateria + 1899.4D));
/* 158 */       if (this.autonomiaBateria > 68) {
/* 159 */         this.autonomiaBateria = 68;
/*     */       }
/* 161 */     } else if (this.potenciaReal < 242.0F) {
/* 162 */       this.autonomiaBateria = Math.round(
/*     */       
/* 164 */         (float)(1.4616D * Math.pow(autonomiaBateria, 2.0D) - 60.009D * autonomiaBateria + 617.29D));
/* 165 */       if (this.autonomiaBateria > 32) {
/* 166 */         this.autonomiaBateria = 32;
/*     */       }
/* 168 */     } else if (this.potenciaReal < 350.0F) {
/* 169 */       this.autonomiaBateria = 
/* 170 */         Math.round((float)(-0.4561D * Math.pow(autonomiaBateria, 2.0D) + 
/* 171 */         25.521D * autonomiaBateria - 336.49D));
/* 172 */       if (this.autonomiaBateria > 16) {
/* 173 */         this.autonomiaBateria = 16;
/*     */       }
/* 175 */     } else if (this.potenciaReal < 470.0F) {
/* 176 */       this.autonomiaBateria = 
/* 177 */         Math.round((float)(0.4426D * Math.pow(autonomiaBateria, 2.0D) - 
/* 178 */         16.549D * autonomiaBateria + 152.03D));
/* 179 */       if (this.autonomiaBateria > 14) {
/* 180 */         this.autonomiaBateria = 14;
/*     */       }
/* 182 */     } else if (this.potenciaReal < 617.0F) {
/* 183 */       this.autonomiaBateria = 
/* 184 */         Math.round((float)(0.2526D * Math.pow(autonomiaBateria, 2.0D) - 
/* 185 */         8.4266D * autonomiaBateria + 66.345D));
/* 186 */       if (this.autonomiaBateria > 12) {
/* 187 */         this.autonomiaBateria = 12;
/*     */       }
/* 189 */     } else if (this.potenciaReal < 810.0F) {
/* 190 */       this.autonomiaBateria = Math.round(
/*     */       
/* 192 */         (float)(0.187D * Math.pow(this.tensaoBateria, 2.0D) - 6.4851D * this.tensaoBateria + 54.985D));
/* 193 */       if (this.autonomiaBateria > 9) {
/* 194 */         this.autonomiaBateria = 9;
/*     */       }
/* 196 */     } else if (this.potenciaReal < 1006.0F) {
/* 197 */       this.autonomiaBateria = 
/* 198 */         Math.round((float)(-0.0116D * Math.pow(autonomiaBateria, 2.0D) + 
/* 199 */         0.9699D * autonomiaBateria - 14.325D));
/* 200 */       if (this.autonomiaBateria > 4)
/* 201 */         this.autonomiaBateria = 4;
/* 202 */     } else if (this.potenciaReal < 1270.0F)
/*     */     {
/* 204 */       this.autonomiaBateria = 
/* 205 */         Math.round((float)(-0.048D * Math.pow(autonomiaBateria, 2.0D) + 
/* 206 */         3.0226D * autonomiaBateria - 40.889D));
/* 207 */       if (this.autonomiaBateria > 4)
/* 208 */         this.autonomiaBateria = 4;
/*     */     }
/* 210 */     if (this.expansorBateria != 0) {
/* 211 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (1.0D + this.expansorBateria / 14.0D)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 220 */     if (this.modoRede) {
/* 221 */       this.tensaoSaida = 
/* 222 */         ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/*     */     }
/*     */     else
/*     */     {
/* 226 */       this.tensaoSaida = 
/* 227 */         Math.round((float)(0.0849D * Math.pow(tensaoSaida, 2.0D) - 10.387D * 
/* 228 */         tensaoSaida + 424.84D));
/* 229 */       if (this.tensaoSaida > 115.0F) {
/* 230 */         this.tensaoSaida = 116.0F;
/*     */       }
/*     */     }
/* 233 */     if (!this.saidaLigada) {
/* 234 */       this.tensaoSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 240 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 250 */     if (isTensaoSaida220()) {
/* 251 */       this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/*     */     } else {
/* 253 */       this.tensaoEntrada = 
/* 254 */         ((int)(this.TENSAO_ENTRADA_F1_115 * tensaoEntrada + this.TENSAO_ENTRADA_F2_115));
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
/* 266 */     if (this.modoRede) {
/* 267 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */     }
/* 270 */     else if (this.saidaLigada) {
/* 271 */       this.correnteSaida = 
/* 272 */         ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 274 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */     
/* 277 */     if (correnteSaida == 0) {
/* 278 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 286 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 295 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 298 */     int mesAtual = calendar.get(2) + 1;
/* 299 */     int anoAtual = calendar.get(1);
/*     */     
/* 301 */     Evento evt = new Evento(29, calendar
/* 302 */       .get(11), calendar.get(12), 
/* 303 */       calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/*     */     
/* 305 */     this.protocoloUPS.addEvento(evt);
/*     */     
/* 307 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 315 */     this.bateriaBaixa = (this.tensaoBateria < 19.0F);
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\PS2200.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */