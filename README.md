# Yocto BSP Layer for SolidRun i.MX6 Cubox-i

## HW Compatibility

- Cubox-i
  - Serial Console: OK
  - microSD: OK
  - RJ45 Ethernet: Untested
  - HDMI: OK
  - 2x USB-A: OK
  - HDMI Digital Stereo Output: OK
  - TOSLINK Digital Stereo Output: Untested
  - eSATA: Untested
  - IR: Untested
- HummingBoard Pro
  - Serial Console: OK
  - microSD: OK
  - RJ45 Ethernet: OK
  - HDMI: OK
  - 2x USB-A: OK
  - 3.5mm Analog Stereo Output: OK
  - 3.5mm Analog Mono Input: Untested
  - HDMI Digital Stereo Output: OK
  - SPDIF Coaxial Digital Stereo Output: Untested
  - mSATA: OK
  - mPCIe PCIe: OK
  - mPCIe USB: OK
  - IR: OK
  - MIPI-CSI: Untested
  - LCDIF: Untested
- HummingBoard Edge:
  - Serial Console: OK
  - microSD: OK
  - RJ45 Ethernet: OK
  - HDMI: OK
  - 4x USB-A: OK
  - 3.5mm Analog Stereo Output: OK
  - 3.5mm Analog Mono Input: Untested
  - HDMI Digital Stereo Output: OK
  - m.2 SATA: OK
  - mPCIe PCIe: Untested
  - mPCIe USB: OK
  - IR: Untested
  - MIPI-CSI: Untested
  - LCDIF: Untested
- HummingBoard CBi:
  - CAN: Untested
  - RS485: Untested
- SolidSense N6: Untested

- SoM Revision 1.3
  - WiFi: OK
  - Bluetooth: OK (only BCM4330)
- SoM Revision 1.5
  - eMMC: OK
  - WiFi: OK
  - Bluetooth: OK
- SoM Revision 1.9: Untested
- SoM Revision 2.0: OK
  - eMMC: OK
  - WiFi: OK
  - Bluetooth: OK

## Compile base image

Start in a new empty directory with plenty of free disk space - at least 100GB.
Then download the build recipes:

    git clone -b kirkstone git://git.yoctoproject.org/poky
    git clone -b kirkstone git://git.openembedded.org/openembedded-core
    git clone -b kirkstone git://git.yoctoproject.org/git/meta-freescale
    git clone -b develop-kirkstone-imx6 https://github.com/Josua-SR/meta-solidrun-arm-imx6.git

Initialise a build directory with example configuration files and appropriate shell environment variables.
Note that this step may be repeated without losing the contents of the build directory, to reinitialise the required environment variables (e.g. `$PATH`):

    cd poky
    . ./oe-init-build-env ../build-sr-imx6

The script will change directory to the specified build directory (`../build-sr-imx6`), and create an example configuration inside a `conf` directory:

    ❯ LANG=C ls -ln conf
    total 24
    -rw-r--r-- 1 1000 100   923 Jan 23 15:55 bblayers.conf
    -rw-r--r-- 1 1000 100 12751 Jan 30 14:36 local.conf
    -rw-r--r-- 1 1000 100    15 Jan 13 16:54 templateconf.cfg

It is **neccessary** to edit `bblayers`.conf. It lists all of the individual folders to import recipes from.
Add both `meta-freescale` and `meta-solidrun-arm-imx6` so that the file looks similar to the example below:

    # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
    # changes incompatibly
    POKY_BBLAYERS_CONF_VERSION = "2"

    BBPATH = "${TOPDIR}"
    BBFILES ?= ""

    BBLAYERS ?= " \
      /opt/workspace/YOCTO/mainline/poky/meta \
      /opt/workspace/YOCTO/mainline/poky/meta-poky \
      /opt/workspace/YOCTO/mainline/poky/meta-yocto-bsp \
      /opt/workspace/YOCTO/mainline/meta-freescale \
      /opt/workspace/YOCTO/mainline/meta-solidrun-arm-imx6 \
      "

To create a bootable image with default configuration it is enough to define the target machine and invoke `bitbake`:

    export MACHINE=imx6qdlcubox
    bitbake core-image-minimal

Aside from `core-image-minimal` two more complete configurations are recommended for evaluation:

- `core-image-full-cmdline`
- `core-image-weston`
- `core-image-weston-sdk`

Results are available in **`tmp/deploy/images/imx6qdlcubox`**.

Builds for `MACHINE=imx6qdlcubox` are usable on the basic reference platforms:
- Cubox-i
- HummingBoard Base/Pro
- HummingBoard Gate/Edge

Specialised boards require selecting a different `MACHINE`:
- HummingBoard CBi: `MACHINE=imx6qdlhummingboardcbi`
- SolidSense N6: `MACHINE=imx6qdlsolidsense`

## Compatible Layers

- [meta-virtualization](git://git.yoctoproject.org/meta-virtualization)

  To enable Docker, add to `conf/local.conf`:

      IMAGE_INSTALL:append = " docker-ce python3-docker-compose kernel-modules"
      DISTRO_FEATURES:append = " virtualization"

- [meta-aws](https://github.com/aws4embeddedlinux/meta-aws.git)

  To enable Amazon Corretto JRE, add to `conf/local.conf`:

      IMAGE_INSTALL:append = " corretto-11-bin"

## Options

### BSP: Mainline / NXP

This layer supports a choice of 2 BSPs:
- Mainline:
  Focused on upstream solutions for hardware accelerators and advanced features of i.MX SoCs.
  Uses e.g. the etnaviv driver for OpenGL and upstream v4l2 drivers for video decoding.
- NXP:
  The official solution provided by NXP.
  Uses e.g. the optimised Vivante Binaries for OpenGL with custom mechanisms for copying frames from and to the video decoding units.
  Generally this option will provide the highest performance - however integration with complex software packages such as desktop environments and web browsers can introduce difficulties.

The underlying meta-freescale chooses "mainline" by default.
It can be customised by adding one choice to `conf/local.conf`:

    IMX_DEFAULT_BSP = "mainline"
    IMX_DEFAULT_BSP = "nxp"

### U-Boot

There are 3 options for U-Boot:

- u-boot-fslc: [NXP community fork of U-Boot](https://github.com/Freescale/u-boot-fslc)
  Mainline U-Boot with patches from the NXP community.
  It adds many not-yet-upstreamed NXP-specific features such as e.g. support for mfgtool; automatic fastboot or RPMB optee support.
- u-boot-yocto: [mainline U-Boot](https://source.denx.de/u-boot/u-boot)
  Generic mainline U-Boot package of the yocto project.
  Only comes with features that are available in mainline U-Boot at time of v2022.01 release.
  Should work okay for i.MX6 SoMs revision 1.9 or earlier.
- u-boot-solidrun-imx6: [SolidRun fork of U-Boot](https://github.com/SolidRun/u-boot/tree/v2018.01-solidrun-imx6)
  Based on v2018.01 release this U-Boot supports all SolidRun i.MX6 SoMs and the standard distro-boot mechanism.
  However it does not come with any of the additional features from u-boot-fslc.
  This is the default and should generally work well.

In `conf/local.conf` add one choice:

    PREFERRED_PROVIDER_virtual/bootloader = "u-boot-fslc"
    PREFERRED_PROVIDER_virtual/bootloader = "u-boot-yocto"
    PREFERRED_PROVIDER_virtual/bootloader = "u-boot-solidrun-imx6"

## HW Interface Basic Tests

### WiFi

    rfkill unblock wlan
    ifconfig wlan0 up
    iw dev wlan0 scan

### Bluetooth

    rfkill unblock bluetooth
    hcitool scan

### IR Receiver

IR receiver can be tested using `ir-keytable` command (which is part of v4l2-utils).
Include with yocto image by adding to `conf/local.conf`:

    IMAGE_INSTALL:append = " ir-keytable"

Then enable all known IR protocols and listen for events:

    root@imx6qdlcubox:~# ir-keytable -p all
    [   57.367472] IR RC5(x/sz) protocol handler initialized
    [   57.462599] IR NEC protocol handler initialized
    [   57.557505] IR RC6 protocol handler initialized
    [   57.654466] IR JVC protocol handler initialized
    [   57.754575] IR Sony protocol handler initialized
    [   57.833052] IR SANYO protocol handler initialized
    [   57.919883] IR Sharp protocol handler initialized
    [   58.001341] IR MCE Keyboard/mouse protocol handler initialized
    [   58.089713] IR XMP protocol handler initialized
    [   58.135292] rc_core: Can't enable IR protocol cec
    [   58.179806] ir_imon_decoder: IR iMON protocol handler initialized
    [   58.274134] IR RCMM protocol handler initialized
    Can't find xbox-dvd bpf protocol in /etc/rc_keymaps/protocols or /lib/udev/rc_keymaps/protocols

    root@imx6qdlcubox:~# ir-keytable -t
    Testing events. Please, press CTRL-C to abort.
    1484.271344: lirc protocol(nec32)[ 1484.283374] evbug: Event. Dev: input0, Type: 0, Code: 0, Value: 0
    : scancode = 0xf802204
    1484.271484: event type EV_MSC(0x04): scancode = 0xf802204
    1484.271484: event type EV_SYN(0x00).

### Sound

Basic test of audio interfaces can be performed using the `speaker-test` command.
To generate 2 channels of sine wave on either interface:

    # 3.5mm Jack
    speaker-test -t sine -f 1000 -c 2 -Ddefault:CARD=Codec

    # HDMI
    speaker-test -t sine -f 1000 -c 2 -Ddefault:CARD=DWHDMI

    # SPDIF
    speaker-test -t sine -f 1000 -c 2 -Ddefault:CARD=SPDIF

Music playback can be tested by using `aplay` music player with any wave file.
For playing an ogg encoded example, include with yocto image by adding to `conf/local.conf`:

    IMAGE_INSTALL:append = " vorbis-tools alsa-utils"


Convert the example music to wave:

    wget https://github.com/KDE/amarok/raw/master/data/first_run_jingle.ogg
    ogg123 -d wav -f first_run_jingle.wav first_run_jingle.ogg

Then play on a soundcard of choice:

    # play to 3.5mm jack
    aplay -D default:CARD=Codec first_run_jingle.wav

    # play to hdmi
    aplay -D default:CARD=DWHDMI first_run_jingle.wav

    # play to spdif
    aplay -D default:CARD=SPDIF first_run_jingle.wav
