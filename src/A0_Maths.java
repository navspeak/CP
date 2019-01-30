
public class A0_Maths {
    /*
     For a compiled program what the executable code sees => a massive block of virtual memory.
     Each location in the virtual memory has an address, which is an integer that starts at 0
     for the first location and goes up to whatever quantity of virtual memory has been assigned to that process.
     Some of the locations in this range will be inaccessible and some will be occupied by the executable code itself.

     There's also a layer of abstraction in the way that virtual memory is mapped from physical memory.
     But none of that matters for our purposes. The mapping from physical memory doesn't change the structure
     of the memory, or the fact that each location is addressed by an integer.

     The CPU also has a small number of registers, the precise configuration depending on the hardware.
     These registers are special locations on which operations on data can be carried out, such as adding numbers or
     checking values, or identifying where in memory the next instruction to be executed is located.
     What concerns us here is what is inside a memory location. Well the answer is that it's a set of what are
     called bits.

     Memory Location = set of bits.
     Now it's important to understand that every single location,
     both in RAM and in the abstracted virtual memory seen by the program contains the same number of bits.
     In other words the structure of any one location in memory is exactly like the structure of any other memory location.
     There's no such thing as a location for say characters and another location for say integers, it's all homogenous.

     How many bits does a location hold?
     In principle that depends on the hardware, but in practice in almost every desktop and laptop machine it'll be 8 bits,
     which is called a byte.
     The size of registers is more variable, these days most new laptops and desktops are 64-bit machines,
     which means amongst other things that each register can hold 64 bits or 8 bytes.

     That's changed over the years as hardware has become cheaper and more sophisticated.
     Back in the days of Windows 3.1, in the early 1990s, typical desktops had 16-bit registers and the Windows
     operating system was designed to work with those 16-bit registers, hence the name 16-bit Windows.
     Windows 95 was designed to run on machines with 32-bit registers,
     while within the last six or seven years 64 bits has become almost universal on typical new machines.
     What I said about all memory locations being structurally the same doesn't quite hold for registers because some
     registers are hard-wired up to perform certain operations, such as floating point arithmetic, or fetching an
     instruction for memory.

     Registers might not all be the same size. For example many x86 processors from 15 or so years ago had 32 bits for
     most of their registers, but 80 bits in the floating points dedicated register. However those registers were still identical to the extent that every register simply holds a set of bits, and those bits can be populated from any location in memory. At the level of the processor, there's no such thing as strong typing. You can load a value from some memory location into the floating point register and then perform floating point operations on that value. Or you can load the same value from the same location into a general purpose register and perform integer operations on that value. It's all a matter of how you interpret the value, and of course of making sure that what your code does to the value makes sense in the context of how you are interpreting it and how you originally stored the value. You'll appreciate the mismatch between the size of registers and the size of memory locations. This is resolved because a data transfer will typically be from a set of contiguous memory locations to a single register, or vice versa. For example, a 64-bit integer may be stored across eight memory locations. When you want to do something with this integer, a single, machine-level instruction will load those 8 bytes simultaneously into the appropriate register. Perhaps the contents of that register will be modified and then its new contents written back to 8 bytes of memory. Alternatively in some cases you may load fewer memory locations. The slide shows 4 bytes being loaded into an 8 byte register, but it could be as little as 1 byte of memory and the remaining bits of the register are filled automatically for example by being zeroed out.
     */
}
