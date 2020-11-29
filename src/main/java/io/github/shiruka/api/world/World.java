/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.world;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents of a world.
 */
public interface World {

  /**
   * obtains the chunk that is located at the given X/Z horizontal plane coordinates.
   *
   * @param x the chunk X coordinate.
   * @param z the chunk Z coordinate.
   *
   * @return the chunk at the given coordinates.
   */
  @NotNull
  Chunk getChunkAt(int x, int z);

  /**
   * obtains a chunk at the given coordinates with an option to not generate the chunk.
   *
   * @param x the x coordinate.
   * @param z the z coordinate.
   * @param gen {@code true} to generate the chunk if it doesn't exist, {@code false} to return null.
   *
   * @return the chunk, or {@code null} if {@code gen} is {@code false} and no chunk is found.
   */
  @Nullable
  Chunk getChunkAt(int x, int z, boolean gen);

  /**
   * obtains the collection of chunks that are currently loaded on this world.
   *
   * @return the collection of loaded chunks.
   */
  @NotNull
  Collection<Chunk> getLoadedChunks();

  /**
   * obtains the name of the world that is represented with the name of the file folder containing the region files.
   *
   * @return the name of the world.
   */
  @NotNull
  String getName();

  /**
   * loads the chunks at spawn.
   */
  void loadSpawnChunks();

  /**
   * saves this world to the region files in the world directory.
   */
  void save();
}