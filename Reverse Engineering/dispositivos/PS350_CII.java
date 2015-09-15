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
/*     */ public class PS350_CII
/*     */   extends PS800
/*     */ {
/*     */   public PS350_CII(ProtocoloPS protocol)
/*     */   {
/*  19 */     super(protocol);
/*  20 */     instanciasGlobais();
/*  21 */     protocol.setModeloUPS(8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void instanciasGlobais()
/*     */   {
/*  29 */     this.TENSAO_ENTRADA_F1 = 1.7875F;
/*  30 */     this.TENSAO_ENTRADA_F2 = 24.582F;
/*  31 */     this.TENSAO_BATERIA_F1 = 0.0711D;
/*  32 */     this.TENSAO_BATERIA_F2 = 0.2525D;
/*     */     
/*  34 */     this.TENSAO_MIN_BATERIA = 10.0F;
/*  35 */     this.TENSAO_MAX_BATERIA = 14.5F;
/*  36 */     this.TENSAO_FLUT_BATERIA = 13.5F;
/*     */     
/*  38 */     this.CORRENTE_SAIDA_F1_MR = 0.0487D;
/*  39 */     this.CORRENTE_SAIDA_F2_MR = 0.1927D;
/*     */     
/*  41 */     this.CORRENTE_SAIDA_F1_MI = 0.0476D;
/*  42 */     this.CORRENTE_SAIDA_F2_MI = 0.2016D;
/*     */     
/*  44 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F1 = new double[] { 0.0469D, 0.0518D, 0.0577D, 0.0D, 0.0462D, 0.0519D, 0.0D, 0.0571D };
/*     */     
/*  46 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F1 = new double[] { 0.575D, 0.0612D, 0.0617D, 0.0D, 0.0551D, 0.0591D, 0.0D, 0.0703D };
/*     */     
/*  48 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F1 = new double[] { 0.0514D, 0.0573D, 0.0673D, 0.0D, 0.0534D, 0.0531D, 0.0D, 0.0621D };
/*     */     
/*     */ 
/*     */ 
/*  52 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F2 = new double[] { 29.599D, 28.41D, 26.039D, 0.0D, 30.108D, 27.038D, 0.0D, 26.522D };
/*     */     
/*  54 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F2 = new double[] { 34.364D, 35.928D, 48.289D, 0.0D, 32.934D, 34.054D, 0.0D, 18.75D };
/*     */     
/*  56 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F2 = new double[] { 61.184D, 52.561D, 29.872D, 0.0D, 43.087D, 60.912D, 0.0D, 43.997D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */     this.POTENCIAUTIL_CURVA1_F1 = new double[] { 0.0469D, 0.0518D, 0.0577D, 0.0D, 0.0462D, 0.0519D, 0.0D, 0.0571D };
/*     */     
/*  64 */     this.POTENCIAUTIL_CURVA2_F1 = new double[] { 0.0451D, 0.0505D, 0.0545D, 0.0D, 0.0462D, 0.0491D, 0.0D, 0.0298D };
/*     */     
/*  66 */     this.POTENCIAUTIL_CURVA3_F1 = new double[] { 0.0421D, 0.0474D, 0.0593D, 0.0D, 0.0438D, 0.0449D, 0.0D, 0.0522D };
/*     */     
/*     */ 
/*     */ 
/*  70 */     this.POTENCIAUTIL_CURVA1_F2 = new double[] { 29.599D, 28.41D, 26.039D, 0.0D, 30.108D, 27.038D, 0.0D, 26.522D };
/*     */     
/*  72 */     this.POTENCIAUTIL_CURVA2_F2 = new double[] { 13.973D, 8.9685D, 16.709D, 0.0D, 12.001D, 15.302D, 0.0D, 57.213D };
/*     */     
/*  74 */     this.POTENCIAUTIL_CURVA3_F2 = new double[] { 23.796D, 15.544D, -4.6501D, 0.0D, 19.27D, 31.405D, 0.0D, 21.36D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */     this.TENSAO_SAIDA_F1_MR = new double[] { 0.9286D, 1.0186D, 1.0414D, 0.0D, 0.8995D, 0.9641D, 0.0D, 0.9507D };
/*  81 */     this.TENSAO_SAIDA_F2_MR = new double[] { 9.9429D, 9.2431D, 16.933D, 0.0D, 13.11D, 14.936D, 0.0D, 26.122D };
/*     */     
/*  83 */     this.TENSAO_SAIDA_F1_MI = new double[] { 13.9D, 14.7D, 15.47D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  84 */     this.TENSAO_SAIDA_F2_MI = new double[] { 11.8D, 10.52D, 11.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/*  94 */     if (this.potenciaReal > 655.0F)
/*     */     {
/*  96 */       this.sobrecarga = true;
/*  97 */       this.cargaElevada = true;
/*     */     }
/*     */     else
/*     */     {
/* 101 */       this.sobrecarga = false;
/* 102 */       this.cargaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*     */   {
/* 112 */     this.limiteSuperiorTensaoSaida = 134.0F;
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
/* 126 */     double pLin = this.POTENCIAUTIL_DETECCAO_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA1_F2[this.estadoReles];
/* 127 */     double pVa = this.POTENCIAUTIL_DETECCAO_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA2_F2[this.estadoReles];
/* 128 */     double pVa2 = this.POTENCIAUTIL_DETECCAO_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA3_F2[this.estadoReles];
/*     */     
/* 130 */     double difLin = pLin - this.potenciaAparente;
/* 131 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 132 */     double difNlin = pVa - this.potenciaAparente;
/* 133 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 134 */     double difNlin2 = pVa2 - this.potenciaAparente;
/* 135 */     if (difNlin2 < 0.0D) { difNlin2 *= -1.0D;
/*     */     }
/* 137 */     if ((difLin < difNlin) && (difLin < difNlin2)) {
/* 138 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA1_F2[this.estadoReles]));
/*     */     }
/* 140 */     else if (difNlin < difNlin2) {
/* 141 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA2_F2[this.estadoReles]));
/*     */     } else {
/* 143 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA3_F2[this.estadoReles]));
/*     */     }
/* 145 */     if (!this.saidaLigada) {
/* 146 */       this.potenciaReal = 0.0F;
/*     */     }
/* 148 */     if (this.potenciaAparente < this.potenciaReal)
/*     */     {
/* 150 */       float f = this.potenciaAparente;
/* 151 */       this.potenciaAparente = this.potenciaReal;
/* 152 */       this.potenciaReal = f;
/*     */     }
/*     */     
/* 155 */     if (potenciaReal == 0)
/*     */     {
/* 157 */       this.potenciaReal = 0.0F;
/*     */     }
/* 159 */     if (this.correnteSaida == 0.0F)
/*     */     {
/* 161 */       this.potenciaReal = 0.0F;
/* 162 */       this.potenciaAparente = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int autonomiaBateria)
/*     */   {
/* 174 */     if (this.potenciaReal < 120.0F)
/*     */     {
/* 176 */       this.autonomiaBateria = Math.round((float)(2.0782D * Math.pow(autonomiaBateria, 2.0D) - 603.65D * autonomiaBateria + 43789.0D));
/* 177 */       this.autonomiaBateria /= 60;
/* 178 */       if (this.autonomiaBateria > 17) {
/* 179 */         this.autonomiaBateria = 17;
/*     */       }
/*     */       
/*     */     }
/* 183 */     else if (this.potenciaReal < 210.0F)
/*     */     {
/* 185 */       this.autonomiaBateria = Math.round((float)(0.455D * Math.pow(autonomiaBateria, 2.0D) - 115.45D * autonomiaBateria + 7297.3D));
/* 186 */       this.autonomiaBateria /= 60;
/* 187 */       if (this.autonomiaBateria > 10) {
/* 188 */         this.autonomiaBateria = 10;
/*     */       }
/*     */     }
/* 191 */     else if (this.potenciaReal < 290.0F)
/*     */     {
/* 193 */       this.autonomiaBateria = Math.round((float)(-0.3321D * Math.pow(autonomiaBateria, 2.0D) + 107.92D * autonomiaBateria - 8483.7D));
/* 194 */       this.autonomiaBateria /= 60;
/* 195 */       if (this.autonomiaBateria > 5) {
/* 196 */         this.autonomiaBateria = 5;
/*     */       }
/*     */       
/*     */     }
/* 200 */     else if (this.potenciaReal < 330.0F)
/*     */     {
/* 202 */       this.autonomiaBateria = Math.round((float)(-0.1636D * Math.pow(autonomiaBateria, 2.0D) + 56.215D * autonomiaBateria - 4564.3D));
/* 203 */       this.autonomiaBateria /= 60;
/* 204 */       if (this.autonomiaBateria > 3) {
/* 205 */         this.autonomiaBateria = 3;
/*     */       }
/*     */     }
/* 208 */     if (this.expansorBateria != 0) {
/* 209 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
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
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 222 */     this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/* 223 */     if (tensaoEntrada == 0) {
/* 224 */       this.tensaoEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 233 */     return true;
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
/* 244 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 247 */     int mesAtual = calendar.get(2) + 1;
/* 248 */     int anoAtual = calendar.get(1);
/*     */     
/* 250 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/*     */     
/* 252 */     this.protocoloUPS.addEvento(evt);
/*     */     
/* 254 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 262 */     if (this.modoBateria) {
/* 263 */       this.bateriaBaixa = (this.tensaoBateria < 11.0F);
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
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 277 */     if (!this.saidaLigada)
/*     */     {
/* 279 */       this.tensaoSaida = 0.0F;
/*     */ 
/*     */     }
/* 282 */     else if (this.modoRede)
/*     */     {
/* 284 */       this.tensaoSaida = ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/* 285 */       if (this.correnteSaida > 2.0F) {
/* 286 */         this.tensaoSaida -= 5.0F;
/*     */       }
/*     */     }
/*     */     else {
/* 290 */       double a = tensaoSaida * 2;
/* 291 */       a /= 128.0D;
/*     */       
/* 293 */       this.tensaoSaida = ((float)(this.tensaoBateria * Math.sqrt(a) * this.TENSAO_SAIDA_F1_MI[this.estadoReles] - this.correnteSaida * this.TENSAO_SAIDA_F2_MI[this.estadoReles]));
/*     */     }
/*     */     
/*     */ 
/* 297 */     if (tensaoSaida == 0) {
/* 298 */       this.tensaoSaida = 0.0F;
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
/* 310 */     if (this.modoRede)
/*     */     {
/* 312 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */ 
/*     */     }
/* 316 */     else if (this.saidaLigada) {
/* 317 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 319 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */     
/* 322 */     if (correnteSaida == 0)
/*     */     {
/* 324 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBateriaCarregada(boolean bateriaCarregada)
/*     */   {
/* 332 */     if (this.modoBateria) {
/* 333 */       this.bateriaCarregada = bateriaCarregada;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaCritica(boolean bateriaCritica)
/*     */   {
/* 340 */     this.bateriaCritica = (this.tensaoBateria < 10.0F);
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\PS350_CII.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */