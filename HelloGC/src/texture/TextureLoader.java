package texture;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class TextureLoader {
    private final ColorModel glAlphaColorModel = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] { 8, 8, 8, 8 }, true, false,
            ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
    private final ColorModel glColorModel = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE,
            DataBuffer.TYPE_BYTE);
    private final IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);

    /**
     * テクスチャーIDを生成する
     */
    private int createTextureID() {
        glGenTextures(textureIDBuffer);

        return textureIDBuffer.get(0);
    }

    /**
     * 指定されたパスの画像ファイルをテクスチャーに変換して返す
     */
    public final Texture loadTexture(final String imagePath) {
        try {
            return loadTexture(ImageIO.read(new FileInputStream(imagePath)));
            //return loadTexture(ImageIO.read(getClass().getClassLoader().getResource(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 指定されたパスの画像ファイルを、指定された ClassLoader で探して、テクスチャーに変換して返す
     */
    public final Texture loadTexture(final String imagePath, final ClassLoader classLoader)
            throws IOException {
        return loadTexture(ImageIO.read(classLoader
                .getResourceAsStream(imagePath)));
    }

    /**
     * BufferedImage をテクスチャーに変換して返す
     */
    public final Texture loadTexture(final BufferedImage image) throws IOException {
        return loadTexture(image, GL_TEXTURE_2D, GL_RGBA, GL_LINEAR, GL_LINEAR);
    }

    private Texture loadTexture(final BufferedImage image, final int target,
            final int dstPixelFormat, final int minFilter, final int magFilter)
            throws IOException {
        // テクスチャー ID を生成する
        int textureID = createTextureID();
        Texture texture = new Texture(target, textureID);

        texture.setWidth(image.getWidth());
        texture.setHeight(image.getHeight());

        // glTexImage2D() の対象となるテクスチャー ID をバインドする
        glBindTexture(target, textureID);

        // アルファ値の有無を確認する
        int srcPixelFormat;

        if (image.getColorModel().hasAlpha()) {
            srcPixelFormat = GL_RGBA;
        } else {
            srcPixelFormat = GL_RGB;
        }

        // BufferedImage をテクスチャー用のバイト配列に変換する
        ByteBuffer textureBuffer = convertImageData(image, texture);

        // 画像の拡大・縮小時の補間方法を設定する
        if (target == GL_TEXTURE_2D) {
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minFilter);
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magFilter);
        }

        // バイト配列と色情報のフォーマットからテクスチャーを生成する
        glTexImage2D(target, 0, dstPixelFormat, get2Fold(image.getWidth()),
                get2Fold(image.getHeight()), 0, srcPixelFormat,
                GL_UNSIGNED_BYTE, textureBuffer);

        textureBuffer.clear();

        return texture;
    }

    /**
     * 指定さえれた値より大きい 2 べき乗を求める
     */
    private static int get2Fold(final int fold) {
        int ret = 2;

        while (ret < fold) {
            ret *= 2;
        }
        return ret;
    }

    /**
     * テクスチャーの元データとなるバイト配列をつくり、BufferedImage　を描画して返す
     */
    private ByteBuffer convertImageData(final BufferedImage bufferedImage,
            final Texture texture) {
        ByteBuffer imageBuffer;
        WritableRaster raster;
        BufferedImage texImage;

        int texWidth = texture.getTextureWidth();
        int texHeight = texture.getTextureHeight();

        if ((texWidth <= 0) || (texHeight <= 0)) {
            texWidth = 2;
            texHeight = 2;

            // テクスチャーの大きさは 2 のべき乗でなければならないため、画像本来の大きさ以上で縦横の長さを計算する
            while (texWidth < bufferedImage.getWidth()) {
                texWidth *= 2;
            }
            while (texHeight < bufferedImage.getHeight()) {
                texHeight *= 2;
            }

            texture.setTextureHeight(texHeight);
            texture.setTextureWidth(texWidth);
        }

        // テクスチャーの元となるデータを作成する
        // 変換する画像がアルファ値を含むかどうかを、テクスチャーのデータ形式に反映させる
        if (bufferedImage.getColorModel().hasAlpha()) {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                    texWidth, texHeight, 4, null);
            texImage = new BufferedImage(glAlphaColorModel, raster, false,
                    new Hashtable<Object, Object>());
        } else {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                    texWidth, texHeight, 3, null);
            texImage = new BufferedImage(glColorModel, raster, false,
                    new Hashtable<Object, Object>());
        }

        // 変換する画像のアルファモードを調べる
        texture.setAlphaPremultiplied((bufferedImage.getType() == BufferedImage.TYPE_4BYTE_ABGR_PRE));

        // テクスチャーの元データに、変換する画像を描画する
        Graphics g = texImage.getGraphics();

        g.setColor(new Color(0f, 0f, 0f, 0f));
        g.fillRect(0, 0, texWidth, texHeight); // 画像は最初に透明色で塗りつぶす
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();

        // 読み込んだ画像を破棄する
        bufferedImage.flush();

        // テクスチャーの元データをバイト配列に変換する
        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer())
                .getData();
        texImage.flush();

        imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();

        return imageBuffer;
    }

    /**
     * テクスチャーに使われるデータ形式と同じデータ形式で、指定のサイズの BufferedImage を生成して返す
     */
    public final BufferedImage createImageData(final int width, final int height) {
        WritableRaster raster = Raster.createInterleavedRaster(
                DataBuffer.TYPE_BYTE, width, height, 4, null);
        BufferedImage bufferedImage = new BufferedImage(glAlphaColorModel,
                raster, true, new Hashtable<Object, Object>());

        return bufferedImage;
    }
}