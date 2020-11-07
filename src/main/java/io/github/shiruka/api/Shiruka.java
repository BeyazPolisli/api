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

package io.github.shiruka.api;

import io.github.shiruka.fragment.FragmentManager;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for Shiru ka server.
 */
public interface Shiruka {

  /**
   * obtains the currently running {@link Server} instance.
   *
   * @return a {@link Server} instance.
   */
  @NotNull
  static Server getServer() {
    return Implementation.getServer();
  }

  /**
   * obtains the {@link FragmentManager} instance.
   *
   * @return a {@link FragmentManager} instance.
   */
  @NotNull
  static FragmentManager getFragmentManager() {
    return Implementation.getFragmentManager();
  }

  /**
   * initiates the server from the given parameters.
   *
   * @param server the server to initiate.
   * @param manager the manager to initiate.
   */
  static void initServer(@NotNull final Server server, @NotNull final FragmentManager manager) {
    Implementation.setServer(server);
    Implementation.setFragmentManager(manager);
  }
}