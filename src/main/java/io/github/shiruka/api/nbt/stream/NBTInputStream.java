package io.github.shiruka.api.nbt.stream;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.ListTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.api.nbt.TagTypes;
import io.github.shiruka.api.nbt.array.ByteArrayTag;
import io.github.shiruka.api.nbt.array.IntArrayTag;
import io.github.shiruka.api.nbt.array.LongArrayTag;
import io.github.shiruka.api.nbt.primitive.ByteTag;
import io.github.shiruka.api.nbt.primitive.DoubleTag;
import io.github.shiruka.api.nbt.primitive.FloatTag;
import io.github.shiruka.api.nbt.primitive.IntTag;
import io.github.shiruka.api.nbt.primitive.LongTag;
import io.github.shiruka.api.nbt.primitive.ShortTag;
import io.github.shiruka.api.nbt.primitive.StringTag;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.Closeable;
import java.io.DataInput;
import java.io.IOException;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an input stream to read named binary tags.
 */
@Getter
@Accessors(fluent = true)
public final class NBTInputStream implements Closeable {

  /**
   * the input.
   */
  @NotNull
  private final DataInput input;

  /**
   * if the stream closed.
   */
  private boolean closed = false;

  /**
   * ctor.
   *
   * @param input the input.
   */
  public NBTInputStream(@NotNull final DataInput input) {
    this.input = input;
  }

  @Override
  public void close() throws IOException {
    if (this.closed) {
      return;
    }
    this.closed = true;
    if (this.input instanceof Closeable) {
      ((Closeable) this.input).close();
    }
  }

  /**
   * reads the given input using the id.
   *
   * @param id the id to read.
   *
   * @return a new tag instance depends on the given id.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public Tag read(final byte id) throws IOException {
    Preconditions.checkState(!this.closed, "Trying to read from a closed reader!");
    switch (id) {
      case 1:
        return this.readByte();
      case 2:
        return this.readShort();
      case 3:
        return this.readInt();
      case 4:
        return this.readLong();
      case 5:
        return this.readFloat();
      case 6:
        return this.readDouble();
      case 7:
        return this.readByteArray();
      case 8:
        return this.readString();
      case 9:
        return this.readListTag();
      case 10:
        return this.readCompoundTag();
      case 11:
        return this.readIntArray();
      case 12:
        return this.readLongArray();
      case 0:
      default:
        throw new IllegalArgumentException("Unknown type " + id);
    }
  }

  /**
   * reads the given input and converts it into the {@link ByteTag}.
   *
   * @return an instance of {@link ByteTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ByteTag readByte() throws IOException {
    return Tag.createByte(this.input.readByte());
  }

  /**
   * reads the given input and converts it into the {@link ByteArrayTag}.
   *
   * @return an instance of {@link ByteArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ByteArrayTag readByteArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new byte[length];
    this.input.readFully(value);
    return Tag.createByteArray(value);
  }

  /**
   * reads the given input and converts it into the {@link CompoundTag}.
   *
   * @return an instance of {@link CompoundTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public CompoundTag readCompoundTag() throws IOException {
    final var compoundTag = Tag.createCompound();
    byte id;
    while ((id = this.input.readByte()) != TagTypes.END.getId()) {
      final var key = this.input.readUTF();
      final var tag = this.read(id);
      compoundTag.set(key, tag);
    }
    return compoundTag;
  }

  /**
   * reads the given input and converts it into the {@link DoubleTag}.
   *
   * @return an instance of {@link DoubleTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public DoubleTag readDouble() throws IOException {
    return Tag.createDouble(this.input.readDouble());
  }

  /**
   * reads the given input and converts it into the {@link FloatTag}.
   *
   * @return an instance of {@link FloatTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public FloatTag readFloat() throws IOException {
    return Tag.createFloat(this.input.readFloat());
  }

  /**
   * reads the given input and converts it into the {@link IntTag}.
   *
   * @return an instance of {@link IntTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public IntTag readInt() throws IOException {
    return Tag.createInt(this.input.readInt());
  }

  /**
   * reads the given input and converts it into the {@link IntArrayTag}.
   *
   * @return an instance of {@link IntArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public IntArrayTag readIntArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new int[length];
    for (var i = 0; i < length; i++) {
      value[i] = this.input.readInt();
    }
    return Tag.createIntArray(value);
  }

  /**
   * reads the given input and converts it into the {@link ListTag}.
   *
   * @return an instance of {@link ListTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ListTag readListTag() throws IOException {
    final var id = this.input.readByte();
    final var length = this.input.readInt();
    final var tags = new ObjectArrayList<Tag>(length);
    for (var i = 0; i < length; i++) {
      final var read = this.read(id);
      tags.add(read);
    }
    return Tag.createList(tags);
  }

  /**
   * reads the given input and converts it into the {@link LongTag}.
   *
   * @return an instance of {@link LongTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public LongTag readLong() throws IOException {
    return Tag.createLong(this.input.readLong());
  }

  /**
   * reads the given input and converts it into the {@link LongArrayTag}.
   *
   * @return an instance of {@link LongArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public LongArrayTag readLongArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new long[length];
    for (var i = 0; i < length; i++) {
      value[i] = this.input.readLong();
    }
    return Tag.createLongArray(value);
  }

  /**
   * reads the given input and converts it into the {@link ShortTag}.
   *
   * @return an instance of {@link ShortTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ShortTag readShort() throws IOException {
    return Tag.createShort(this.input.readShort());
  }

  /**
   * reads the given input and converts it into the {@link StringTag}.
   *
   * @return an instance of {@link StringTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public StringTag readString() throws IOException {
    return Tag.createString(this.input.readUTF());
  }
}
