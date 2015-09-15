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
/*     */ 
/*     */ 
/*     */ public class SolisM13
/*     */   extends SolisM11
/*     */ {
/*  16 */   protected int[] calcAut0100 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 5, 6, 7, 9, 10, 12, 15, 16, 18, 22, 25, 27, 30, 36, 42, 45, 48, 55, 60, 64, 70, 78, 84, 90, 97, 104 };
/*     */   
/*  18 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 2, 3, 4, 5, 6, 7, 8, 10, 12, 13, 14, 16, 19, 22, 25, 28, 30, 35, 38, 42, 46, 52, 55, 58, 60, 62 };
/*     */   
/*  20 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 7, 9, 10, 12, 13, 16, 20, 21, 23, 26, 28, 30, 34, 35, 37, 38, 39, 40, 40, 40 };
/*     */   
/*  22 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 18, 21, 22, 24, 27 };
/*     */   
/*  24 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 19, 20, 22, 23 };
/*     */   
/*  26 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18 };
/*     */   
/*  28 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 5, 7, 7, 7, 8, 9, 10, 11, 12, 14, 14 };
/*     */   
/*  30 */   protected int[] calcAut0800 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 8, 8, 9, 10, 11, 12, 13, 13, 13 };
/*     */   
/*  32 */   protected int[] calcAut0900 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 11, 11, 12 };
/*     */   
/*  34 */   protected int[] calcAut1000 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM13(ProtocoloUPS protocolo)
/*     */   {
/*  42 */     super(protocolo);
/*  43 */     protocolo.setModeloUPS(13);
/*  44 */     instanciaGlobais();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM13() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/*  57 */     this.potenciaNominalVA = 2000;
/*     */     
/*  59 */     this.potenciaNominalW = 2000;
/*     */     
/*  61 */     this.frequenciaEntradaC = 101620.0F;
/*     */     
/*  63 */     this.correnteEntradaF1MR = 22.2F;
/*     */     
/*  65 */     this.correnteEntradaF2MR = 800.0F;
/*     */     
/*  67 */     this.tensaoEntradaOffset = 30.0F;
/*     */     
/*  69 */     this.comparadorTensaoEntrada = 194;
/*     */     
/*  71 */     this.tensaoEntradaF1194 = 2.5F;
/*     */     
/*  73 */     this.tensaoEntradaF2194 = -250.0F;
/*     */     
/*  75 */     this.tensaoEntradaF1193 = 1.141F;
/*     */     
/*  77 */     this.tensaoEntradaF2193 = 13.0F;
/*     */     
/*  79 */     this.tensaoBateriaF1 = 0.14285715F;
/*     */     
/*  81 */     this.tensaoBateriaF2 = 0.0F;
/*     */     
/*  83 */     this.bateriaMaximoMR = 27.0F;
/*     */     
/*  85 */     this.bateriaMinimoMR = 23.0F;
/*     */     
/*  87 */     this.bateriaMaximoMB = 26.0F;
/*     */     
/*  89 */     this.bateriaMinimoMB = 21.0F;
/*     */     
/*  91 */     this.comparadorAutonomiaBateria = 5;
/*     */     
/*  93 */     this.comparadorContadorAutonomiaBaixa = 5;
/*     */     
/*  95 */     this.correnteSaida220F1MR = 0.03076923F;
/*     */     
/*  97 */     this.correnteSaida220F2MR = 0.0F;
/*     */     
/*  99 */     this.correnteSaida220F1MB = 0.03076923F;
/*     */     
/* 101 */     this.correnteSaida220F2MB = 0.0F;
/*     */     
/* 103 */     this.correnteSaida110F1MR = 0.060606062F;
/*     */     
/* 105 */     this.correnteSaida110F2MR = 0.1F;
/*     */     
/* 107 */     this.correnteSaida110F1MB = 0.059523813F;
/*     */     
/* 109 */     this.correnteSaida110F2MB = 0.0F;
/*     */     
/* 111 */     this.tensaoSaida220F1MR = 2.8F;
/*     */     
/* 113 */     this.tensaoSaida220F2MR = 20.0F;
/*     */     
/* 115 */     this.tensaoSaida220F1MB = 2.9F;
/*     */     
/* 117 */     this.tensaoSaida220F2MB = 18.0F;
/*     */     
/* 119 */     this.tensaoSaida110F1MR = 1.375F;
/*     */     
/* 121 */     this.tensaoSaida110F2MR = 16.0F;
/*     */     
/* 123 */     this.tensaoSaida110F1MB = 1.41F;
/*     */     
/* 125 */     this.tensaoSaida110F2MB = 16.0F;
/*     */     
/* 127 */     this.potenciaReal220F1MR = 0.099009894F;
/*     */     
/* 129 */     this.potenciaReal220F2MR = 26.0F;
/*     */     
/* 131 */     this.potenciaReal220F1MB = 0.10638298F;
/*     */     
/* 133 */     this.potenciaReal220F2MB = 30.0F;
/*     */     
/* 135 */     this.potenciaReal110F1MR = 0.09803922F;
/*     */     
/* 137 */     this.potenciaReal110F2MR = 11.0F;
/*     */     
/* 139 */     this.potenciaReal110F1MB = 0.10638298F;
/*     */     
/* 141 */     this.potenciaReal110F2MB = 15.0F;
/*     */     
/* 143 */     this.potenciaAparenteOffset = 35.0F;
/*     */     
/* 145 */     this.potenciaAparente220F1MR = 0.09433962F;
/*     */     
/* 147 */     this.potenciaAparente220F2MR = 26.0F;
/*     */     
/* 149 */     this.potenciaAparente220F1MB = 0.10204081F;
/*     */     
/* 151 */     this.potenciaAparente220F2MB = 30.0F;
/*     */     
/* 153 */     this.potenciaAparente110F1MR = 0.09803922F;
/*     */     
/* 155 */     this.potenciaAparente110F2MR = 11.0F;
/*     */     
/* 157 */     this.potenciaAparente110F1MB = 0.10638298F;
/*     */     
/* 159 */     this.potenciaAparente110F2MB = 15.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int tensaoBateriaBruta)
/*     */   {
/* 170 */     if (this.potenciaReal <= 20.0F) {
/* 171 */       this.autonomiaBateria = 170;
/* 172 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 175 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 178 */       if (tensaoBateriaBruta > 177) {
/* 179 */         this.autonomiaBateria = 164;
/*     */       }
/* 181 */       if (tensaoBateriaBruta < 141) {
/* 182 */         this.autonomiaBateria = 0;
/*     */       }
/* 184 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 187 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 190 */       if (tensaoBateriaBruta > 177) {
/* 191 */         this.autonomiaBateria = 95;
/*     */       }
/* 193 */       if (tensaoBateriaBruta < 141) {
/* 194 */         this.autonomiaBateria = 0;
/*     */       }
/* 196 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 199 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 202 */       if (tensaoBateriaBruta > 177) {
/* 203 */         this.autonomiaBateria = 64;
/*     */       }
/* 205 */       if (tensaoBateriaBruta < 141) {
/* 206 */         this.autonomiaBateria = 0;
/*     */       }
/* 208 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 211 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 214 */       if (tensaoBateriaBruta > 172) {
/* 215 */         this.autonomiaBateria = 44;
/*     */       }
/* 217 */       if (tensaoBateriaBruta < 141) {
/* 218 */         this.autonomiaBateria = 0;
/*     */       }
/* 220 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 223 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 226 */       if (tensaoBateriaBruta > 173) {
/* 227 */         this.autonomiaBateria = 36;
/*     */       }
/* 229 */       if (tensaoBateriaBruta < 141) {
/* 230 */         this.autonomiaBateria = 0;
/*     */       }
/* 232 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 235 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 238 */       if (tensaoBateriaBruta > 172) {
/* 239 */         this.autonomiaBateria = 28;
/*     */       }
/* 241 */       if (tensaoBateriaBruta < 141) {
/* 242 */         this.autonomiaBateria = 0;
/*     */       }
/* 244 */     } else if ((this.potenciaReal > 650.0F) && (this.potenciaReal <= 750.0F))
/*     */     {
/*     */       try {
/* 247 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 250 */       if (tensaoBateriaBruta > 172) {
/* 251 */         this.autonomiaBateria = 22;
/*     */       }
/* 253 */       if (tensaoBateriaBruta < 141) {
/* 254 */         this.autonomiaBateria = 0;
/*     */       }
/* 256 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 850.0F))
/*     */     {
/*     */       try {
/* 259 */         this.autonomiaBateria = this.calcAut0800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException7) {}
/* 262 */       if (tensaoBateriaBruta > 172) {
/* 263 */         this.autonomiaBateria = 20;
/*     */       }
/* 265 */       if (tensaoBateriaBruta < 141) {
/* 266 */         this.autonomiaBateria = 0;
/*     */       }
/* 268 */     } else if ((this.potenciaReal > 850.0F) && (this.potenciaReal <= 950.0F))
/*     */     {
/*     */       try {
/* 271 */         this.autonomiaBateria = this.calcAut0900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException8) {}
/* 274 */       if (tensaoBateriaBruta > 172) {
/* 275 */         this.autonomiaBateria = 16;
/*     */       }
/* 277 */       if (tensaoBateriaBruta < 141) {
/* 278 */         this.autonomiaBateria = 0;
/*     */       }
/* 280 */     } else if (this.potenciaReal > 950.0F)
/*     */     {
/*     */       try {
/* 283 */         this.autonomiaBateria = this.calcAut1000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException9) {}
/* 286 */       if (tensaoBateriaBruta > 172) {
/* 287 */         this.autonomiaBateria = 14;
/*     */       }
/* 289 */       if (tensaoBateriaBruta < 141) {
/* 290 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 294 */     if (this.expansorBateria > 0) {
/* 295 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisM13.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */