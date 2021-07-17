/*    */ package ru.mbutakov.auroracryptofarm.utils;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Scanner;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*    */ import net.minecraft.client.resources.IResourcePack;
/*    */ import net.minecraft.client.resources.data.IMetadataSection;
/*    */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*    */ import net.minecraft.client.resources.data.PackMetadataSection;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.ResourceLocation;
import ru.mbutakov.auroracryptofarm.Main;
import ru.mbutakov.auroracryptofarm.client.ClientEvents;
/*    */ 
/*    */ public class ShaderResourcePack implements IResourcePack, IResourceManagerReloadListener {
/*    */   protected boolean validPath(ResourceLocation location) {
/* 28 */     return (location.getResourceDomain().equals("minecraft") && location.getResourcePath().startsWith("shaders/"));
/*    */   }
/*    */   
/* 31 */   private final Map<ResourceLocation, String> loadedData = new HashMap<>();
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation location) throws IOException {
/* 35 */     if (validPath(location)) {
/* 36 */       String s = this.loadedData.computeIfAbsent(location, loc -> {
/*    */             InputStream in = Main.class.getResourceAsStream("/" + location.getResourcePath());
/*    */             StringBuilder data = new StringBuilder();
/*    */             Scanner scan = new Scanner(in);
/*    */             try {
/*    */               while (scan.hasNextLine())
/*    */                 data.append(scan.nextLine().replaceAll("@radius@", Integer.toString(20))).append('\n'); 
/*    */             } finally {
/*    */               scan.close();
/*    */             } 
/*    */             return data.toString();
/*    */           });
/* 50 */       return new ByteArrayInputStream(s.getBytes());
/*    */     } 
/* 52 */     throw new FileNotFoundException(location.toString());
/*    */   }
/*    */   
/*    */   public boolean resourceExists(ResourceLocation location) {
/* 57 */     return (validPath(location) && Main.class.getResource("/" + location.getResourcePath()) != null);
/*    */   }
/*    */   
/*    */   public Set<String> getResourceDomains() {
/* 62 */     return (Set<String>)ImmutableSet.of("minecraft");
/*    */   }
/*    */   
/*    */   public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
/* 67 */     return (IMetadataSection)new PackMetadataSection((IChatComponent)new ChatComponentText("Blur's default shaders"), 3);
/*    */   }
/*    */   
/*    */   public BufferedImage getPackImage() throws IOException {
/* 72 */     throw new FileNotFoundException("pack.png");
/*    */   }
/*    */   
/*    */   public String getPackName() {
/* 77 */     return "Blur dummy resource pack";
/*    */   }
/*    */   
/*    */   public void onResourceManagerReload(IResourceManager resourceManager) {
/* 82 */     this.loadedData.clear();
/*    */   }
/*    */ }


/* Location:              E:\WorkMb\libs\Blur_M3C1.7.10_1.0.1_2.jar!\com\tterrag\blu\\util\ShaderResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */