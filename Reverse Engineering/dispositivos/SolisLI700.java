/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SolisLI700
/*     */   extends SolisM11
/*     */ {
/*  14 */   protected int[] calcAut0100 = { 1, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 19, 22, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24 };
/*  15 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
/*  16 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
/*  17 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
/*  18 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisLI700(ProtocoloUPS protocolo)
/*     */   {
/*  27 */     setProtocoloUPS(protocolo);
/*  28 */     protocolo.setModeloUPS(9);
/*  29 */     instanciaGlobais();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/*  37 */     this.temperaturaCritica = 100.0F;
/*  38 */     this.potenciaNominalVA = 700;
/*  39 */     this.potenciaNominalW = 500;
/*  40 */     this.frequenciaEntradaC = 101580.0F;
/*  41 */     this.tensaoSaida220F1MB = 3.15F;
/*  42 */     this.tensaoSaida220F2MB = 2.0F;
/*  43 */     this.tensaoSaida220F1MR = 2.9F;
/*  44 */     this.tensaoSaida220F2MR = 13.0F;
/*  45 */     this.tensaoSaida110F1MB = 1.428F;
/*  46 */     this.tensaoSaida110F2MB = 12.8F;
/*  47 */     this.tensaoSaida110F1MR = 1.428F;
/*  48 */     this.tensaoSaida110F2MR = 12.8F;
/*  49 */     this.tensaoEntradaOffset = 30.0F;
/*  50 */     this.comparadorTensaoEntrada = 106;
/*  51 */     this.tensaoEntradaF1193 = 1.3F;
/*  52 */     this.tensaoEntradaF2193 = 19.5F;
/*  53 */     this.tensaoEntradaF1194 = 1.25F;
/*  54 */     this.tensaoEntradaF2194 = 27.5F;
/*  55 */     this.tensaoBateriaF1 = 0.155F;
/*  56 */     this.tensaoBateriaF2 = 0.105F;
/*  57 */     this.correnteSaida220F1MB = 0.16051364F;
/*  58 */     this.correnteSaida220F2MB = 0.67F;
/*  59 */     this.correnteSaida220F1MR = 0.15360983F;
/*  60 */     this.correnteSaida220F2MR = 0.7F;
/*  61 */     this.correnteSaida110F1MB = 0.16051364F;
/*  62 */     this.correnteSaida110F2MB = 0.67F;
/*  63 */     this.correnteSaida110F1MR = 0.15360983F;
/*  64 */     this.correnteSaida110F2MR = 0.7F;
/*  65 */     this.potenciaAparenteOffset = 55.0F;
/*  66 */     this.potenciaAparente220F1MR = 0.26F;
/*  67 */     this.potenciaAparente220F2MR = 13.7F;
/*  68 */     this.potenciaAparente220F1MB = 0.064935066F;
/*  69 */     this.potenciaAparente220F2MB = 22.0F;
/*  70 */     this.potenciaAparente110F1MR = (this.correnteSaida110F1MR * this.tensaoSaida110F1MR);
/*  71 */     this.potenciaAparente110F2MR = (this.correnteSaida110F2MR + this.tensaoSaida110F2MR);
/*  72 */     this.potenciaAparente110F1MB = (this.correnteSaida110F1MB * this.tensaoSaida110F1MB);
/*  73 */     this.potenciaAparente110F2MB = (this.correnteSaida110F2MB + this.tensaoSaida110F2MB);
/*  74 */     this.potenciaReal220F1MR = 0.26F;
/*  75 */     this.potenciaReal220F2MR = 70.0F;
/*  76 */     this.potenciaReal220F1MB = 0.27F;
/*  77 */     this.potenciaReal220F2MB = 71.0F;
/*  78 */     this.potenciaReal110F1MR = 0.26F;
/*  79 */     this.potenciaReal110F2MR = 70.0F;
/*  80 */     this.potenciaReal110F1MB = 0.27F;
/*  81 */     this.potenciaReal110F2MB = 71.0F;
/*  82 */     this.correnteEntradaF1MR = 22.2F;
/*  83 */     this.correnteEntradaF2MR = 800.0F;
/*  84 */     this.bateriaMaximoMR = 27.0F;
/*  85 */     this.bateriaMinimoMR = 23.0F;
/*  86 */     this.bateriaMaximoMB = 26.0F;
/*  87 */     this.bateriaMinimoMB = 21.0F;
/*  88 */     this.comparadorAutonomiaBateria = 5;
/*  89 */     this.comparadorContadorAutonomiaBaixa = 50;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*     */   {
/*  95 */     if (this.tensaoEntrada > 160.0F) {
/*  96 */       this.limiteInferiorTensaoEntrada = 160.0F;
/*     */     } else {
/*  98 */       this.limiteInferiorTensaoEntrada = 75.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada) {
/* 103 */     if (this.tensaoEntrada > 180.0F) {
/* 104 */       this.limiteSuperiorTensaoEntrada = 300.0F;
/*     */     } else {
/* 106 */       this.limiteSuperiorTensaoEntrada = 160.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFrequenciaEntrada(int frequenciaEntrada) {
/* 111 */     if (this.modoBateria) {
/*     */       try {
/* 113 */         this.frequenciaEntrada = (this.frequenciaEntradaC / frequenciaEntrada);
/*     */       } catch (Exception e) {
/* 115 */         this.frequenciaEntrada = 0.0F;
/*     */       }
/*     */     } else {
/*     */       try
/*     */       {
/* 120 */         this.frequenciaEntrada = (this.frequenciaEntradaC / frequenciaEntrada - 0.8F);
/*     */       } catch (Exception e) {
/* 122 */         this.frequenciaEntrada = 0.0F;
/*     */       }
/*     */     }
/* 125 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/* 126 */       this.frequenciaEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPotenciaAparente(int potenciaAparente)
/*     */   {
/* 133 */     this.potenciaAparente = (this.correnteSaida * this.tensaoSaida);
/* 134 */     if (this.potenciaAparente == this.potenciaAparenteOffset)
/* 135 */       this.potenciaAparente = 0.0F;
/*     */   }
/*     */   
/*     */   public void setCorrenteSaida(int correnteSaida) {
/* 139 */     if (!this.modoRede)
/*     */     {
/* 141 */       if (this.tensaoSaida220) {
/* 142 */         this.correnteSaida = (correnteSaida * this.correnteSaida220F1MB + this.correnteSaida220F2MB);
/*     */       } else {
/* 144 */         this.correnteSaida = (correnteSaida * this.correnteSaida110F1MB + this.correnteSaida110F2MB);
/*     */       }
/*     */       
/*     */     }
/* 148 */     else if (this.tensaoSaida220) {
/* 149 */       this.correnteSaida = (correnteSaida * this.correnteSaida220F1MR + this.correnteSaida220F2MR);
/*     */     } else {
/* 151 */       this.correnteSaida = (correnteSaida * this.correnteSaida110F1MR + this.correnteSaida110F2MR);
/*     */     }
/* 153 */     if (correnteSaida == 0) {
/* 154 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setAutonomiaBateria(int tensaoBateriaBruta) {
/* 159 */     if (this.potenciaReal <= 20.0F) {
/* 160 */       this.autonomiaBateria = 170;
/* 161 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 164 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 136)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 167 */       if (tensaoBateriaBruta > 156) {
/* 168 */         this.autonomiaBateria = 24;
/*     */       }
/* 170 */       if (tensaoBateriaBruta < 136) {
/* 171 */         this.autonomiaBateria = 0;
/*     */       }
/* 173 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 176 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 136)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 179 */       if (tensaoBateriaBruta > 154) {
/* 180 */         this.autonomiaBateria = 10;
/*     */       }
/* 182 */       if (tensaoBateriaBruta < 136) {
/* 183 */         this.autonomiaBateria = 0;
/*     */       }
/* 185 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 188 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 136)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 191 */       if (tensaoBateriaBruta > 156) {
/* 192 */         this.autonomiaBateria = 7;
/*     */       }
/* 194 */       if (tensaoBateriaBruta < 136) {
/* 195 */         this.autonomiaBateria = 0;
/*     */       }
/* 197 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 200 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 136)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 203 */       if (tensaoBateriaBruta > 155) {
/* 204 */         this.autonomiaBateria = 5;
/*     */       }
/* 206 */       if (tensaoBateriaBruta < 136) {
/* 207 */         this.autonomiaBateria = 0;
/*     */       }
/* 209 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 212 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 136)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 215 */       if (tensaoBateriaBruta > 149) {
/* 216 */         this.autonomiaBateria = 3;
/*     */       }
/* 218 */       if (tensaoBateriaBruta < 136) {
/* 219 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 223 */     if (this.expansorBateria > 0)
/* 224 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*     */   }
/*     */   
/*     */   public boolean isTemperaturaAvaliable() {
/* 228 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisLI700.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */